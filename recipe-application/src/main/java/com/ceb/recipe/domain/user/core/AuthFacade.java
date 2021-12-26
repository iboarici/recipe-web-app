package com.ceb.recipe.domain.user.core;

import com.ceb.recipe.domain.user.core.model.AddUserCommand;
import com.ceb.recipe.domain.user.core.model.Exception.UserAlreadyExistsException;
import com.ceb.recipe.domain.user.core.model.Exception.UserNotFoundException;
import com.ceb.recipe.domain.user.core.model.User;
import com.ceb.recipe.domain.user.core.ports.incoming.AuthenticationOperations;
import com.ceb.recipe.domain.user.core.ports.incoming.UserOperations;
import com.ceb.recipe.domain.user.core.ports.outgoing.UserDatabase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class AuthFacade implements UserOperations, AuthenticationOperations {

    private final UserDatabase userDatabase;

    @Override
    public User loadUser(String username) throws UserNotFoundException {
        Optional<User> user = userDatabase.findByUsername(username);
        return user.orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public List<User> getAll() {
        return userDatabase.getAll();
    }

    @Override
    public void addNewUser(AddUserCommand addUserCommand) throws UserAlreadyExistsException {
        Optional<User> user = userDatabase.findByUsername(addUserCommand.getUsername());
        user.ifPresent(u -> {
            throw new UserAlreadyExistsException(addUserCommand.getUsername());
        });
        userDatabase.save(User.builder().username(addUserCommand.getUsername()).password(addUserCommand.getPassword()).role(addUserCommand.getRole()).build());
    }

    @Override
    public void deleteUser(String username) throws UserNotFoundException {
        Optional<User> user = userDatabase.findByUsername(username);
        user.orElseThrow(() -> new UserNotFoundException(username));
        userDatabase.delete(username);
    }
}
