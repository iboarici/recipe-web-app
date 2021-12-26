package com.ceb.recipe.domain.user;

import com.ceb.recipe.domain.user.core.model.User;
import com.ceb.recipe.domain.user.core.ports.outgoing.UserDatabase;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


public class InMemoryUserDatabase implements UserDatabase {

    ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    public InMemoryUserDatabase() {
        users.put("admin", User.builder().username("admin").build());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User user = users.get(username);
        return user == null ? Optional.empty() : Optional.of(user);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void delete(String username) {

    }
}
