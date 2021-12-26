package com.ceb.recipe.domain.inventory.core.model.exceptions;

public class IngredientNotFoundException extends RuntimeException {

    public IngredientNotFoundException(String message) {
        super("Ingredient Not Found : " + message, null, false, false);
    }
}
