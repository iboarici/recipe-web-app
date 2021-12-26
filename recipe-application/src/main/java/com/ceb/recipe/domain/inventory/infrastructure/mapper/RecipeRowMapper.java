package com.ceb.recipe.domain.inventory.infrastructure.mapper;

import com.ceb.recipe.domain.inventory.core.model.Recipe;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeRowMapper implements RowMapper<Recipe> {

    @Override
    public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Recipe.builder()
                .id(rs.getLong("ID"))
                .title(rs.getString("TITLE"))
                .creationDate(rs.getTimestamp("CREATE_DATE").toLocalDateTime())
                .vegetarian(rs.getBoolean("VEGETARIAN"))
                .suitableForNumberOfPeople(rs.getInt("SUITABLE_PEOPLE_COUNT"))
                .prepareTimeInMinutes(rs.getInt("PREPARE_TIME_IN_MINUTES"))
                .cookingTimeInMinutes(rs.getInt("COOKING_TIME_IN_MINUTES"))
                .calories(rs.getInt("CALORIES"))
                .imageUrl(rs.getString("IMAGE_URL"))
                .videoUrl(rs.getString("VIDEO_URL"))
                .createdUser(rs.getString("CREATED_USER"))
                .category(rs.getString("CATEGORY"))
                .build();
    }
}