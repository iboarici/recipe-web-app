package com.ceb.recipe.domain.inventory.infrastructure;

import com.ceb.recipe.domain.inventory.core.model.*;
import com.ceb.recipe.domain.inventory.core.ports.outgoing.InventoryDatabase;
import com.ceb.recipe.domain.inventory.infrastructure.mapper.CategoryRowMapper;
import com.ceb.recipe.domain.inventory.infrastructure.mapper.CookingInstructionRowMapper;
import com.ceb.recipe.domain.inventory.infrastructure.mapper.IngredientRowMapper;
import com.ceb.recipe.domain.inventory.infrastructure.mapper.RecipeRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
public class InventoryDatabaseAdapter implements InventoryDatabase {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> result = new ArrayList<>();
        try {
            result = jdbcTemplate.query("SELECT R.ID, R.TITLE, R.CREATE_DATE, R.VEGETARIAN, R.SUITABLE_PEOPLE_COUNT, R.PREPARE_TIME_IN_MINUTES, R.COOKING_TIME_IN_MINUTES, R.CALORIES, R.IMAGE_URL, R.VIDEO_URL, R.CREATED_USER, C.NAME as CATEGORY FROM RECIPES R, CATEGORIES C WHERE R.CATEGORY_ID = C.ID LIMIT 50", new RecipeRowMapper());
            if (!result.isEmpty()) {
                result.forEach(recipe -> {
                    recipe.setIngredients(findIngredientsByRecipeId(recipe.getId()));
                    recipe.setCookingInstructions(findCookingInstructionsByRecipeId(recipe.getId()));
                });
            }
        } catch (DataAccessException e) {
            log.error("Exception has been occured while retreiving all recipes. Error: {}", e.getMessage());
        }
        return result;
    }

    @Override
    public Optional<Recipe> findRecipeById(Long id) {
        try {
            List<Recipe> result = jdbcTemplate.query("SELECT R.ID, R.TITLE, R.CREATE_DATE, R.VEGETARIAN, R.SUITABLE_PEOPLE_COUNT, R.PREPARE_TIME_IN_MINUTES, R.COOKING_TIME_IN_MINUTES, R.CALORIES, R.IMAGE_URL, R.VIDEO_URL, R.CREATED_USER, C.NAME as CATEGORY FROM RECIPES R, CATEGORIES C WHERE R.CATEGORY_ID = C.ID AND R.ID = ?", new RecipeRowMapper(), id);
            if (result.isEmpty()) {
                return Optional.empty();
            }
            Recipe recipe = result.get(0);
            if (recipe != null) {
                recipe.setIngredients(findIngredientsByRecipeId(recipe.getId()));
                recipe.setCookingInstructions(findCookingInstructionsByRecipeId(recipe.getId()));
                //recipe.setCategory(getCategoryById(recipe.getId()));
            }
            return Optional.of(recipe);
        } catch (DataAccessException e) {
            log.error("Exception has been occured while retreiving recipe by id: {}", id);
            return Optional.empty();
        }
    }

    @Override
    public List<Ingredient> findIngredientsByRecipeId(Long id) {
        List<Ingredient> result = new ArrayList<>();
        try {
            result = jdbcTemplate.query("SELECT * FROM INGREDIENTS WHERE RECIPE_ID = ?", new IngredientRowMapper(), id);
        } catch (DataAccessException e) {
            log.error("Exception has been occured while ingredients by recipeId: {}", id);
        }
        return result;
    }

    @Override
    public List<CookingInstruction> findCookingInstructionsByRecipeId(Long id) {
        List<CookingInstruction> result = new ArrayList<>();
        try {
            result = jdbcTemplate.query("SELECT * FROM COOKING_INSTRUCTIONS WHERE RECIPE_ID = ? ORDER BY ORDER_ID", new CookingInstructionRowMapper(), id);
        } catch (DataAccessException e) {
            log.error("Exception has been occured while ingredients by recipeId: {}", id);
        }
        return result;
    }

    private Long getCategoryByName(String name) {
        try {
            return jdbcTemplate.queryForObject("SELECT C.ID FROM CATEGORIES C WHERE C.NAME = ?", Long.class, name);
        } catch (DataAccessException e) {
            log.error("Exception has been occured while getting Categories by name: {}", name);
        }
        return null;
    }

    @Override
    public List<Recipe> findRecipeByCriteria(SearchCriteria searchCriteria) {
        Pair<String, List<Object>> sqlAndParameters = buildSqlStatement(searchCriteria);
        List<Recipe> result = new ArrayList<>();
        try {
            result = jdbcTemplate.query(sqlAndParameters.getFirst(), new RecipeRowMapper(), sqlAndParameters.getSecond().toArray());
            searchCriteria.getIncludeCookingInstructions();
            result.forEach(recipe -> {
                if (searchCriteria.includeIngredients()) {
                    recipe.setIngredients(findIngredientsByRecipeId(recipe.getId()));
                }

                if (searchCriteria.includeCookingInstructions()) {
                    recipe.setCookingInstructions(findCookingInstructionsByRecipeId(recipe.getId()));
                }
            });
        } catch (DataAccessException e) {
            log.error("Exception has been occured while retreiving recipe by searchCriteria: {}", searchCriteria.toString());
        }
        return result;

    }

    @Override
    public boolean isRecipeExistByTitle(String title) {
        Integer count = new Integer(0);
        try {
            count = jdbcTemplate.queryForObject("SELECT COUNT(ID) FROM RECIPES WHERE TITLE = ?", Integer.class, title);
        } catch (DataAccessException e) {
            log.error("Exception has been occured while checkRecipeExistByTitle: {}", title);
        }
        return count.intValue() > 0;
    }

    @Override
    public Long saveRecipe(Recipe recipe) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            Long categoryId = getCategoryByName(recipe.getCategory());

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO RECIPES (TITLE, CREATE_DATE, VEGETARIAN, SUITABLE_PEOPLE_COUNT, PREPARE_TIME_IN_MINUTES, COOKING_TIME_IN_MINUTES, CALORIES, IMAGE_URL, VIDEO_URL, CREATED_USER, CATEGORY_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, recipe.getTitle());
                ps.setTimestamp(2, java.sql.Timestamp.valueOf(recipe.getCreationDate()));
                ps.setBoolean(3, recipe.isVegetarian());
                ps.setInt(4, recipe.getSuitableForNumberOfPeople());
                ps.setInt(5, recipe.getPrepareTimeInMinutes());
                ps.setInt(6, recipe.getCookingTimeInMinutes());
                ps.setInt(7, recipe.getCalories());
                ps.setString(8, recipe.getImageUrl());
                ps.setString(9, recipe.getVideoUrl());
                ps.setString(10, recipe.getCreatedUser());
                ps.setLong( 11, categoryId);
                return ps;
            }, keyHolder);
            return Long.valueOf(keyHolder.getKeys().get("ID").toString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return 0L;
    }

    @Override
    public void addIngredientToRecipe(Long recipeId, Ingredient ingredient) {
        try {
            jdbcTemplate.update("INSERT INTO INGREDIENTS (RECIPE_ID, NAME, QUANTITY, UNIT, SIZE, ADDITONAL_INFO) VALUES(?, ?, ?, ?, ?, ?)", recipeId, ingredient.getName(), ingredient.getQuantity(), ingredient.getUnit(), ingredient.getSize(), ingredient.getAdditionalInfo());
        } catch (DataAccessException e) {
            log.error("An error has been occured while adding Ingredient to Recipe. RecipeId: {}, Ingredient: {}", recipeId, ingredient);
        }
    }

    @Override
    public void addCookingInstructionToRecipe(Long recipeId, CookingInstruction cookingInstruction) {
        try {
            jdbcTemplate.update("INSERT INTO COOKING_INSTRUCTIONS (RECIPE_ID, ORDER_ID, DESCRIPTION, IMAGE_URL, VIDEO_URL) VALUES (?, ?, ?, ?, ?)", recipeId, cookingInstruction.getOrderId(), cookingInstruction.getDescription(), cookingInstruction.getImageUrl(), cookingInstruction.getVideoUrl());
        } catch (DataAccessException e) {
            log.error("An error has been occured while adding Cooking Instruction to Recipe. RecipeId: {}, CookingInstruction: {}", recipeId, cookingInstruction);
        }
    }

    /**
     *
     * @param id
     */
    @Override
    public void deleteRecipe(Long id) {
        try {
            jdbcTemplate.update("DELETE FROM FAVORITE_RECIPES WHERE RECIPE_ID = ?", id);
            jdbcTemplate.update("DELETE FROM COOKING_INSTRUCTIONS WHERE RECIPE_ID = ?", id);
            jdbcTemplate.update("DELETE FROM INGREDIENTS WHERE RECIPE_ID = ?", id);
            jdbcTemplate.update("DELETE FROM RECIPES WHERE ID = ?", id);
        } catch (DataAccessException e) {
            log.error("Exception has been occured while deleting Recipe: {}", id);
        }
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        Long categoryId = getCategoryByName(recipe.getCategory());
        jdbcTemplate.update("UPDATE RECIPES SET TITLE = ?, CREATE_DATE = ?, VEGETARIAN = ?, SUITABLE_PEOPLE_COUNT = ?, PREPARE_TIME_IN_MINUTES = ?, COOKING_TIME_IN_MINUTES = ?, CALORIES = ?, IMAGE_URL = ?, VIDEO_URL = ?, CREATED_USER = ?, CATEGORY_ID = ? WHERE ID = ?",
                recipe.getTitle(), recipe.getCreationDate(), recipe.isVegetarian(), recipe.getSuitableForNumberOfPeople(), recipe.getPrepareTimeInMinutes(), recipe.getCookingTimeInMinutes(), recipe.getCalories(), recipe.getImageUrl(), recipe.getVideoUrl(), recipe.getCreatedUser(), categoryId, recipe.getId());

        jdbcTemplate.update("DELETE FROM INGREDIENTS WHERE RECIPE_ID = ?", recipe.getId());
        jdbcTemplate.update("DELETE FROM COOKING_INSTRUCTIONS WHERE RECIPE_ID = ?", recipe.getId());
        if (Objects.nonNull(recipe.getIngredients())) {
            recipe.getIngredients().forEach(ingredient -> addIngredientToRecipe(recipe.getId(), ingredient));
        }

        if (Objects.nonNull(recipe.getCookingInstructions())) {
            recipe.getCookingInstructions().forEach(cookingInstruction -> addCookingInstructionToRecipe(recipe.getId(), cookingInstruction));
        }
    }

    @Override
    public boolean existsInFavorites(String username, Long recipeId) {
        Integer count = new Integer(0);
        try {
            count = jdbcTemplate.queryForObject("SELECT COUNT(RECIPE_ID) FROM FAVORITE_RECIPES WHERE USERNAME = ? and RECIPE_ID = ?", Integer.class, username, recipeId);
        } catch (DataAccessException e) {
            log.error("Exception has been occured in existsInFavorites. username: {}, recipeId: {}", username, recipeId);
        }
        return count.intValue() > 0;
    }

    @Override
    public void addToFavorites(String username, Long recipeId) {
        try {
            jdbcTemplate.update("INSERT INTO FAVORITE_RECIPES (RECIPE_ID, USERNAME) VALUES(?, ?)", recipeId, username);
        } catch (DataAccessException e) {
            log.error("Exception has been occured while addToFavorites. user: {}, recipeId: {}", username, recipeId);
        }
    }


    @Override
    public void removeFromFavorites(String username, Long recipeId) {
        try {
            jdbcTemplate.update("DELETE FROM FAVORITE_RECIPES WHERE RECIPE_ID = ? AND USERNAME = ?", recipeId, username);
        } catch (DataAccessException e) {
            log.error("Exception has been occured while addToFavorites. user: {}, recipeId: {}", username, recipeId);
        }
    }

    @Override
    public List<Recipe> getFavoriteRecipes(String username) {
        List<Long> favorites = new ArrayList<>();
        List<Recipe> recipes = new ArrayList<>();

        try {
            favorites = jdbcTemplate.queryForList("SELECT RECIPE_ID FROM FAVORITE_RECIPES WHERE USERNAME = ? LIMIT 100", Long.class, username);
            favorites.forEach(aLong -> findRecipeById(aLong).ifPresent(recipes::add));
        } catch (DataAccessException e) {
            log.error("Exception has been occured while getting Favorite Recipes for user: {}", username);
        }

        return recipes;
    }

    @Override
    public boolean isCategoryExistByName(String name) {
        Integer count = new Integer(0);
        try {
            count = jdbcTemplate.queryForObject("SELECT COUNT(ID) FROM CATEGORIES WHERE NAME = ?", Integer.class, name);
        } catch (DataAccessException e) {
            log.error("Exception has been occured while isCategoryExistByName: {}, Error: {}", name, e.getMessage());
        }
        return count.intValue() > 0;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            categories = jdbcTemplate.query("SELECT * FROM CATEGORIES", new CategoryRowMapper());
        } catch (DataAccessException e) {
            log.error("Exception has been occured while retrieving all categories from db. Error: {}", e.getMessage());
        }
        return categories;
    }

    @Override
    public void addCategory(Category category) {
        try {
            jdbcTemplate.update("INSERT INTO CATEGORIES(NAME) VALUES(?)", category.getName());
        } catch (DataAccessException e) {
            log.error("Exception has been occured while adding new category. Category Name {}, Error: {}", category.getName(), e.getMessage());
        }
    }

    @Override
    public void removeCategory(Category category) {
        try {
            jdbcTemplate.update("DELETE FROM CATEGORIES WHERE ID = ?", category.getId());
        } catch (DataAccessException e) {
            log.error("Exception has been occured while removing category. Category Name {}, Error: {}", category.getName(), e.getMessage());
        }
    }

    @Override
    public void updateCategory(Category category) {
        try {
            jdbcTemplate.update("UPDATE CATEGORIES SET NAME = ? WHERE ID = ?", category.getName(), category.getId());
        } catch (DataAccessException e) {
            log.error("Exception has been occured while updating category. Category Name {}, Error: {}", category.getName(), e.getMessage());
        }
    }

    private Pair<String, List<Object>> buildSqlStatement(SearchCriteria searchCriteria) {
        StringBuilder sb = new StringBuilder("SELECT R.ID, R.TITLE, R.CREATE_DATE, R.VEGETARIAN, R.SUITABLE_PEOPLE_COUNT, R.PREPARE_TIME_IN_MINUTES, R.COOKING_TIME_IN_MINUTES, R.CALORIES, R.IMAGE_URL, R.VIDEO_URL, R.CREATED_USER, C.NAME as CATEGORY FROM RECIPES R, CATEGORIES C WHERE R.CATEGORY_ID = C.ID");
        List<Object> parameters = new ArrayList<>();
        if (searchCriteria.searchByTitle()) {
            sb.append(" AND R.TITLE = ?");
            parameters.add(searchCriteria.getTitle());
        }
        if (searchCriteria.searchByDateRange()) {
            if (Objects.nonNull(searchCriteria.getCreateDateSTART())) {
                sb.append(" AND R.CREATE_DATE >= ?");
                parameters.add(searchCriteria.getCreateDateSTART());
            }
            if (Objects.nonNull(searchCriteria.getCreateDateEND())) {
                sb.append(" AND R.CREATE_DATE <= ?");
                parameters.add(searchCriteria.getCreateDateEND());
            }
        }
        if (searchCriteria.searchByVegetarian()) {
            sb.append(" AND R.VEGETARIAN = ?");
            parameters.add(searchCriteria.getVegetarian());
        }
        if (searchCriteria.searchBySuitableForNumberEqual()) {
            sb.append(" AND R.SUITABLE_PEOPLE_COUNT = ?");
            parameters.add(searchCriteria.getSuitableForNumberOfPeopleEQUAL());
        }
        if (searchCriteria.searchBySuitableForNumberRange()) {
            if (Objects.nonNull(searchCriteria.getSuitableForNumberOfPeopleGTE())) {
                sb.append(" AND R.SUITABLE_PEOPLE_COUNT >= ?");
                parameters.add(searchCriteria.getSuitableForNumberOfPeopleGTE());
            } else if (Objects.nonNull(searchCriteria.getSuitableForNumberOfPeopleGT())) {
                sb.append(" AND R.SUITABLE_PEOPLE_COUNT > ?");
                parameters.add(searchCriteria.getSuitableForNumberOfPeopleGT());
            }
            if (Objects.nonNull(searchCriteria.getSuitableForNumberOfPeopleLTE())) {
                sb.append(" AND R.SUITABLE_PEOPLE_COUNT <= ?");
                parameters.add(searchCriteria.getSuitableForNumberOfPeopleLTE());
            } else if (Objects.nonNull(searchCriteria.getSuitableForNumberOfPeopleLT())) {
                sb.append(" AND R.SUITABLE_PEOPLE_COUNT < ?");
                parameters.add(searchCriteria.getSuitableForNumberOfPeopleLT());
            }
        }

        if (searchCriteria.searchByPrepareTimeEQUAL()) {
            sb.append(" AND R.PREPARE_TIME_IN_MINUTES = ?");
            parameters.add(searchCriteria.searchByPrepareTimeEQUAL());
        }

        if (searchCriteria.searchByPrepareTimeRange()) {
            if (Objects.nonNull(searchCriteria.getPrepareTimeInMinutesGTE())) {
                sb.append(" AND R.PREPARE_TIME_IN_MINUTES >= ?");
                parameters.add(searchCriteria.getPrepareTimeInMinutesGTE());
            } else if (Objects.nonNull(searchCriteria.getPrepareTimeInMinutesGT())) {
                sb.append(" AND R.PREPARE_TIME_IN_MINUTES > ?");
                parameters.add(searchCriteria.getPrepareTimeInMinutesGT());
            }
            if (Objects.nonNull(searchCriteria.getPrepareTimeInMinutesLTE())) {
                sb.append(" AND R.PREPARE_TIME_IN_MINUTES <= ?");
                parameters.add(searchCriteria.getPrepareTimeInMinutesLTE());
            } else if (Objects.nonNull(searchCriteria.getPrepareTimeInMinutesLT())) {
                sb.append(" AND R.PREPARE_TIME_IN_MINUTES < ?");
                parameters.add(searchCriteria.getPrepareTimeInMinutesLT());
            }
        }

        if (searchCriteria.searchByCookingTimeEQUAL()) {
            sb.append(" AND R.COOKING_TIME_IN_MINUTES = ?");
            parameters.add(searchCriteria.getCookingTimeInMinutesEQUAL());
        }

        if (searchCriteria.searchByCookingTimeRange()) {
            if (Objects.nonNull(searchCriteria.getCookingTimeInMinutesGTE())) {
                sb.append(" AND R.COOKING_TIME_IN_MINUTES >= ?");
                parameters.add(searchCriteria.getCookingTimeInMinutesGTE());
            } else if (Objects.nonNull(searchCriteria.getCookingTimeInMinutesGT())) {
                sb.append(" AND R.COOKING_TIME_IN_MINUTES > ?");
                parameters.add(searchCriteria.getCookingTimeInMinutesGT());
            }
            if (Objects.nonNull(searchCriteria.getCookingTimeInMinutesLTE())) {
                sb.append(" AND R.COOKING_TIME_IN_MINUTES <= ?");
                parameters.add(searchCriteria.getCookingTimeInMinutesLTE());
            } else if (Objects.nonNull(searchCriteria.getCookingTimeInMinutesLT())) {
                sb.append(" AND R.COOKING_TIME_IN_MINUTES < ?");
                parameters.add(searchCriteria.getCookingTimeInMinutesLT());
            }
        }

        if (searchCriteria.searchByCaloriesEQUAL()) {
            sb.append(" AND R.CALORIES = ?");
            parameters.add(searchCriteria.getCaloriesEQUAL());
        }

        if (searchCriteria.searchByCaloriesRange()) {
            if (Objects.nonNull(searchCriteria.getCaloriesGTE())) {
                sb.append(" AND R.CALORIES >= ?");
                parameters.add(searchCriteria.getCaloriesGTE());
            } else if (Objects.nonNull(searchCriteria.getCaloriesGT())) {
                sb.append(" AND R.CALORIES > ?");
                parameters.add(searchCriteria.getCaloriesGT());
            }
            if (Objects.nonNull(searchCriteria.getCaloriesLTE())) {
                sb.append(" AND R.CALORIES <= ?");
                parameters.add(searchCriteria.getCaloriesLTE());
            } else if (Objects.nonNull(searchCriteria.getCaloriesLT())) {
                sb.append(" AND R.CALORIES < ?");
                parameters.add(searchCriteria.getCaloriesLT());
            }
        }

        if (searchCriteria.searchByUsers()) {
            String inSql = String.join(",", Collections.nCopies(searchCriteria.getUsers().size(), "?"));
            sb.append(String.format(" AND CREATED_USER IN (%s)", inSql));
            parameters.add(searchCriteria.getUsers().toArray());
        }

        return Pair.of(sb.toString(), parameters);
    }
}
