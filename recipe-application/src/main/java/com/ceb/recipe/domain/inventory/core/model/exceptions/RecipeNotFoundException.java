package com.ceb.recipe.domain.inventory.core.model.exceptions;

public class RecipeNotFoundException extends RuntimeException {

    public RecipeNotFoundException(String message) {
        super("Recipe Not Found : " + message, null, false, false);
    }
}
