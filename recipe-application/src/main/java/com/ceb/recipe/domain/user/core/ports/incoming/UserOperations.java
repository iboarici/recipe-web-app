package com.ceb.recipe.domain.user.core.ports.incoming;

import com.ceb.recipe.domain.user.core.model.AddUserCommand;
import com.ceb.recipe.domain.user.core.model.Exception.UserAlreadyExistsException;
import com.ceb.recipe.domain.user.core.model.Exception.UserNotFoundException;
import com.ceb.recipe.domain.user.core.model.User;

import java.util.List;

public interface UserOperations {

    List<User> getAll();

    void addNewUser(AddUserCommand addUserCommand) throws UserAlreadyExistsException;

    void deleteUser(String username) throws UserNotFoundException;
}
