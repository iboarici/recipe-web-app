package com.ceb.recipe.domain.user;

import com.ceb.recipe.domain.user.core.AuthFacade;
import com.ceb.recipe.domain.user.core.model.AddUserCommand;
import com.ceb.recipe.domain.user.core.model.Exception.UserAlreadyExistsException;
import com.ceb.recipe.domain.user.core.model.Exception.UserNotFoundException;
import com.ceb.recipe.domain.user.core.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
class AuthFacadeTest {

    private AuthFacade authFacade;

    @BeforeEach
    void setUp() {
        InMemoryUserDatabase inMemoryUserDatabase = new InMemoryUserDatabase();
        authFacade = new AuthFacade(inMemoryUserDatabase);
    }

    @Test
    void should_ThrowUserNotFoundException_WhenUserNotExist() {
        //Given
        String username = "unknown_user";

        //When
        Executable executable = () -> authFacade.loadUser(username);

        //Then
        assertThrows(UserNotFoundException.class,executable);
    }

    @Test
    void should_ReturnCorrectUser_WhenUserExist() {
        //Given
        String username = "admin";

        //When
        User user = authFacade.loadUser(username);

        //Then
        assertEquals(user.getUsername(), username);
    }

    @Test
    void should_ThrowsUserAlreadyExistsException_AddingExistingUser() {
        //Given
        AddUserCommand addUserCommand = AddUserCommand.builder().username("admin").build();

        //When
        Executable executable = () -> authFacade.addNewUser(addUserCommand);

        //Then
        assertThrows(UserAlreadyExistsException.class,executable);
    }

    @Test
    void should_ThrowsUserNotFoundException_DeletingNotExistingUser() {
        //Given
        String username = "unknown_user";

        //When
        Executable executable = () -> authFacade.deleteUser(username);

        //Then
        assertThrows(UserNotFoundException.class, executable);
    }
}