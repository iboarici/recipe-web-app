package com.ceb.recipe.domain.inventory.core.ports.incoming;

import com.ceb.recipe.domain.inventory.core.model.Recipe;
import com.ceb.recipe.domain.inventory.core.model.exceptions.RecipeAlreadyInFavoritesException;
import com.ceb.recipe.domain.inventory.core.model.exceptions.RecipeNotFoundException;
import com.ceb.recipe.domain.inventory.core.model.exceptions.RecipeNotInFavoritesException;

import java.util.List;

public interface FavoriteOperations {

    List<Recipe> getFavoriteRecipes(String username);

    void addToFavorites(String username, Long recipeId) throws RecipeAlreadyInFavoritesException, RecipeNotFoundException;

    void removeFromFavorites(String username, Long recipeId) throws RecipeNotInFavoritesException;
}
