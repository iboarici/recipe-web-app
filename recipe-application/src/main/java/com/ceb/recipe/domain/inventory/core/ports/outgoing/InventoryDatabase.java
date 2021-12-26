package com.ceb.recipe.domain.inventory.core.ports.outgoing;

import com.ceb.recipe.domain.inventory.core.model.*;

import java.util.List;
import java.util.Optional;

public interface InventoryDatabase {


    Optional<Recipe> findRecipeById(Long id);

    List<Ingredient> findIngredientsByRecipeId(Long id);

    List<CookingInstruction> findCookingInstructionsByRecipeId(Long id);

    List<Recipe> getAllRecipes();

    List<Recipe> findRecipeByCriteria(SearchCriteria searchCriteria);

    boolean isRecipeExistByTitle(String title);

    Long saveRecipe(Recipe recipe);

    void addIngredientToRecipe(Long recipeId, Ingredient ingredient);

    void addCookingInstructionToRecipe(Long recipeId, CookingInstruction cookingInstruction);

    void deleteRecipe(Long id);

    void updateRecipe(Recipe recipe);

    boolean existsInFavorites(String username, Long recipeId);

    void addToFavorites(String username, Long recipeId);

    void removeFromFavorites(String username, Long recipeId);

    List<Recipe> getFavoriteRecipes(String username);

    boolean isCategoryExistByName(String name);

    List<Category> getAllCategories();

    void addCategory(Category category);

    void removeCategory(Category category);

    void updateCategory(Category category);
}
