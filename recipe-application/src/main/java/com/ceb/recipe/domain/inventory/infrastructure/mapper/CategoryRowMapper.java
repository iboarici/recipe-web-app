package com.ceb.recipe.domain.inventory.infrastructure.mapper;

import com.ceb.recipe.domain.inventory.core.model.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CategoryRowMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Category.builder()
                .id(rs.getLong("ID"))
                .name(rs.getString("NAME")).build();
    }
}
