package com.ceb.recipe.domain.user.core.ports.incoming;

import com.ceb.recipe.domain.user.core.model.Exception.UserNotFoundException;
import com.ceb.recipe.domain.user.core.model.User;

public interface AuthenticationOperations {

    User loadUser(String username) throws UserNotFoundException;
}
