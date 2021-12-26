package com.ceb.recipe.domain.user.it;

import com.ceb.recipe.domain.user.core.model.AddUserCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String username = "admin";
    private static final String password = "admin123";
    private static final String CONTENT_TYPE = "application/json";


    @Test
   void should_ReturnRole_WhenUserAuthenticatedCorrectCredentials() throws Exception {
       String expected = "ADMIN";

       this.mockMvc.perform(MockMvcRequestBuilders.get("/auth").with(httpBasic(username, password)))
               .andExpect(status().isOk()).andExpect(content().string(expected));
   }

    @Test
    void should_ReturnUnauthorized_WhenUserAuthenticatedWrongCredentials() throws Exception {
        String incorrectPassword = "incorrect";
        this.mockMvc.perform(MockMvcRequestBuilders.get("/auth").with(httpBasic(username, incorrectPassword)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void should_ReturnAllUsers_WhenUserAuthenticatedCorrectCredentials() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user").with(httpBasic(username, password)))
                .andExpect(status().isOk()).andExpect(content().contentType(CONTENT_TYPE));
    }

    @Test
    void should_ReturnBadRequest_WhenAddUserWithInvalidValues() throws Exception {
        //Given
        AddUserCommand addUserCommand =  AddUserCommand.builder().username("sa").password("sa").role("EDITOR").build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/user").contentType(CONTENT_TYPE).content(asJsonString(addUserCommand)).with(httpBasic(username, password)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_ReturnCreated_WhenAddUser() throws Exception {
        //Given
        AddUserCommand addUserCommand =  AddUserCommand.builder().username("sample").password("sample123").role("USER").build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/user").contentType(CONTENT_TYPE).content(asJsonString(addUserCommand)).with(httpBasic(username, password)))
                .andExpect(status().isCreated());
    }

    @Test
    void should_Return404_WhenDeletingNonExistingUser() throws Exception  {
        String nonExistingUser = "sample";
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/user/{username}", nonExistingUser).with(httpBasic(username, password)))
                .andExpect(status().isNotFound());
    }

    @Test
    void should_Return200_WhenDeletingUser() throws Exception  {
        String nonExistingUser = "sample";
        AddUserCommand addUserCommand =  AddUserCommand.builder().username("sample").password("sample123").role("USER").build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/user").contentType(CONTENT_TYPE).content(asJsonString(addUserCommand)).with(httpBasic(username, password)));

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/user/{username}", nonExistingUser).with(httpBasic(username, password)))
                .andExpect(status().isOk());

    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}