package com.ceb.recipe.domain.user.infrastructure;

import com.ceb.recipe.domain.user.core.model.User;
import com.ceb.recipe.domain.user.core.ports.outgoing.UserDatabase;
import com.ceb.recipe.domain.user.infrastructure.mapper.UserRowMapper;
import com.ceb.recipe.domain.user.infrastructure.mapper.UserWithoutPasswordRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserDatabaseAdapter implements UserDatabase {

    private final JdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            List<User> result = jdbcTemplate.query("SELECT * FROM USERS WHERE username = ?", new UserRowMapper(), username);
            if (result.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(result.get(0));
        } catch (DataAccessException e) {
            log.error("Exception has been occured while retreiving user: {}", username);
            return Optional.empty();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            users = jdbcTemplate.query("SELECT username, role FROM USERS", new UserWithoutPasswordRowMapper());
        } catch (DataAccessException e) {
            log.error("Exception has been occured while retreiving all users: {}");
        }
        return users;
    }

    @Override
    public void save(User user) {
        try {
            jdbcTemplate.update("INSERT INTO USERS (username, password, role) VALUES (?, ?, ?)", user.getUsername(), passwordEncoder.encode(user.getPassword()), user.getRole());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String username) {
        try {
            jdbcTemplate.update("DELETE FROM USERS WHERE username = ?", username);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}