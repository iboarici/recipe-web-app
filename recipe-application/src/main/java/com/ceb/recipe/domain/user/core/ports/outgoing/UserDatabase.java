package com.ceb.recipe.domain.user.core.ports.outgoing;

import com.ceb.recipe.domain.user.core.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDatabase {

    Optional<User> findByUsername(String username);

    List<User> getAll();

    void save(User user);

    void delete(String username);
}
