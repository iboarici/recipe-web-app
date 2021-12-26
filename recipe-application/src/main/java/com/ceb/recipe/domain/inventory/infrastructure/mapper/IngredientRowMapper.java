package com.ceb.recipe.domain.inventory.infrastructure.mapper;

import com.ceb.recipe.domain.inventory.core.model.Ingredient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientRowMapper implements RowMapper<Ingredient> {

    @Override
    public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Ingredient.builder()
                .name((rs.getString("NAME")))
                .quantity(rs.getFloat("QUANTITY"))
                .unit(rs.getString("UNIT"))
                .size(rs.getString("SIZE"))
                .additionalInfo(rs.getString("ADDITONAL_INFO"))
                .build();
    }
}
