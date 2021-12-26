package com.ceb.recipe.domain.inventory.core.ports.incoming;

import com.ceb.recipe.domain.inventory.core.model.Recipe;
import com.ceb.recipe.domain.inventory.core.model.SearchCriteria;
import com.ceb.recipe.domain.inventory.core.model.exceptions.RecipeDuplicateTitleException;
import com.ceb.recipe.domain.inventory.core.model.exceptions.RecipeNotFoundException;

import java.util.List;

public interface RecipeOperations {

    List<Recipe> getAllRecipes(String username);

    Recipe findRecipeById(Long id) throws RecipeNotFoundException;

    List<Recipe> findRecipeByCriteria(String username, SearchCriteria searchCriteria) throws RecipeNotFoundException;

    Long addNewRecipe(Recipe recipe) throws RecipeDuplicateTitleException;

    void deleteRecipe(Long recipeId) throws RecipeNotFoundException;

    void updateRecipe(Recipe recipe);

}
