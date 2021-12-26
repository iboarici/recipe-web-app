package com.ceb.recipe.domain.inventory.core.model.exceptions;

public class RecipeAlreadyInFavoritesException extends RuntimeException {

    public RecipeAlreadyInFavoritesException(String username, Long recipeId) {
        super("Recipe Already In Favorites : " + username + ", " + recipeId, null, false, false);
    }
}
