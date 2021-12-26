package com.ceb.recipe.domain.inventory.it;

import com.ceb.recipe.domain.inventory.application.model.AddRecipeCommand;
import com.ceb.recipe.domain.inventory.application.model.CookingInstructionCommand;
import com.ceb.recipe.domain.inventory.application.model.IngredientCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String username = "admin";
    private static final String password = "admin123";
    private static final String CONTENT_TYPE = "application/json";

    @Test
    void should_ReturnOKStatus_WhenGettingAllRecipes() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/recipe/").with(httpBasic(username, password)))
                .andExpect(status().isOk()).andExpect(content().contentType(CONTENT_TYPE));
    }

    @Test
    void should_ReturnNotFoundStatus_WhenFindingNonExistingRecipe() throws Exception {
        Long id = 10L;
        this.mockMvc.perform(MockMvcRequestBuilders.get("/recipe/{id}", id).with(httpBasic(username, password)))
                .andExpect(status().isNotFound());
    }

    @Test
    void should_ReturnRecipe_WhenFindingRecipeById() throws Exception {
        Long id = 1L;
        this.mockMvc.perform(MockMvcRequestBuilders.get("/recipe/{id}", id).with(httpBasic(username, password)))
                .andExpect(status().isOk()).andExpect(content().contentType(CONTENT_TYPE));
    }

    @Test
    void should_ReturnNextId_WhenAddingNewRecipe() throws Exception {
        AddRecipeCommand addRecipeCommand =  new AddRecipeCommand();
        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setName("Sample Ingredient");
        ingredient.setQuantity(1.0F);
        ingredient.setSize("Middle");
        ingredient.setUnit("Kg");
        CookingInstructionCommand cookingInstructionCommand = new CookingInstructionCommand();
        cookingInstructionCommand.setOrderId(1);
        cookingInstructionCommand.setDescription("Sample cooking instruction text");
        addRecipeCommand.setTitle("Sample Food");
        addRecipeCommand.setCalories(800);
        addRecipeCommand.setCategory("Lunch");
        addRecipeCommand.setPrepareTimeInMinutes(30);
        addRecipeCommand.setCookingTimeInMinutes(30);
        addRecipeCommand.setCreationDate(LocalDateTime.now());
        addRecipeCommand.setSuitableForNumberOfPeople(3);
        addRecipeCommand.setIngredients(Arrays.asList(ingredient));
        addRecipeCommand.setCookingInstructions(Arrays.asList(cookingInstructionCommand));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/recipe").
                contentType(CONTENT_TYPE).content(asJsonString(addRecipeCommand)).with(httpBasic(username, password)))
                .andExpect(status().isCreated()).andExpect(content().string("8"));
    }

    @Test
    void should_ReturnStatusOk_WhenUpdatingRecipe() throws Exception {
        AddRecipeCommand addRecipeCommand =  new AddRecipeCommand();
        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setName("Sample Ingredient");
        ingredient.setQuantity(1.0F);
        ingredient.setSize("Middle");
        ingredient.setUnit("Kg");
        CookingInstructionCommand cookingInstructionCommand = new CookingInstructionCommand();
        cookingInstructionCommand.setOrderId(1);
        cookingInstructionCommand.setDescription("Sample cooking instruction text");
        addRecipeCommand.setTitle("Updated Food");
        addRecipeCommand.setCalories(800);
        addRecipeCommand.setCategory("Lunch");
        addRecipeCommand.setPrepareTimeInMinutes(30);
        addRecipeCommand.setCookingTimeInMinutes(30);
        addRecipeCommand.setCreationDate(LocalDateTime.now());
        addRecipeCommand.setSuitableForNumberOfPeople(3);
        addRecipeCommand.setIngredients(Arrays.asList(ingredient));
        addRecipeCommand.setCookingInstructions(Arrays.asList(cookingInstructionCommand));

        this.mockMvc.perform(MockMvcRequestBuilders.put("/recipe/{id}", 8L)
                        .contentType(CONTENT_TYPE).content(asJsonString(addRecipeCommand)).with(httpBasic(username, password)))
                .andExpect(status().isOk());
    }

    @Test
    void should_ReturnStatusOk_WhenDeletingRecipe() throws Exception  {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/recipe/{id}", 8L)
                        .contentType(CONTENT_TYPE).with(httpBasic(username, password)))
                .andExpect(status().isOk());
    }

    @Test
    void should_ReturnOk_WhenAddToFavorites() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/recipe/favorites/{id}", 1)
                        .contentType(CONTENT_TYPE).with(httpBasic(username, password)))
                .andExpect(status().isOk());
    }

    @Test
    void should_ReturnOk_WhenRemovingFromFavorites() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/recipe/favorites/{id}", 1)
                        .contentType(CONTENT_TYPE).with(httpBasic(username, password)))
                .andExpect(status().isOk());
    }

    @Test
    void removeFromFavorites() {
        //Given

        //When

        //Then
    }

    private String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JavaTimeModule javaTimeModule=new JavaTimeModule();
            objectMapper.registerModule(javaTimeModule);
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}