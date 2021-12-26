package com.ceb.recipe.domain.user.it;

import com.ceb.recipe.domain.user.core.model.AddUserCommand;
import com.ceb.recipe.domain.user.core.model.Exception.UserAlreadyExistsException;
import com.ceb.recipe.domain.user.core.model.Exception.UserNotFoundException;
import com.ceb.recipe.domain.user.core.ports.incoming.UserOperations;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AuthIntegrationTests {

    @Qualifier("userOperations")
    @Autowired
    private UserOperations userOperations;

    @Test
    void should_ThrowsUserAlreadyExistsException_AddingExistingUser() {
        //Given
        AddUserCommand addUserCommand = AddUserCommand.builder().username("admin").build();

        //When
        Executable executable = () -> userOperations.addNewUser(addUserCommand);

        //Then
        assertThrows(UserAlreadyExistsException.class,executable);
    }

    @Test
    void should_ThrowsUserNotFoundException_DeletingNotExistingUser() {
        //Given
        String username = "unknown_user";

        //When
        Executable executable = () -> userOperations.deleteUser(username);

        //Then
        assertThrows(UserNotFoundException.class, executable);
    }
}
