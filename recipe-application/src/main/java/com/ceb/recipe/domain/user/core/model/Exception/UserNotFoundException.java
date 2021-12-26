package com.ceb.recipe.domain.user.core.model.Exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username) {
        super("There is no active user with name: " + username, null, false, false);
    }
}
