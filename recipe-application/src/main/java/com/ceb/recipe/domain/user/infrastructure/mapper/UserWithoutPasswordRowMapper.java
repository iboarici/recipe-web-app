package com.ceb.recipe.domain.user.infrastructure.mapper;

import com.ceb.recipe.domain.user.core.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserWithoutPasswordRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .username(rs.getString("username"))
                .role(rs.getString("role"))
                .build();
    }
}
