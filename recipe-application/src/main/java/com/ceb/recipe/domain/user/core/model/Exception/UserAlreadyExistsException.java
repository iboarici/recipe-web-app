package com.ceb.recipe.domain.user.core.model.Exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String username) {
        super("There is already a user with name: " + username, null, false, false);
    }
}
