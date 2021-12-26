package com.ceb.recipe.domain.inventory.core.model.exceptions;

public class RecipeNotInFavoritesException extends RuntimeException {

    public RecipeNotInFavoritesException(String username, Long recipeId) {
        super("Recipe Not in Favorites : " + username + ", " + recipeId, null, false, false);
    }
}
