package com.ceb.recipe.domain.inventory.core.model.exceptions;

public class CookingInstructionNotFoundException extends RuntimeException {

    public CookingInstructionNotFoundException(String message) {
        super("Ingredient Not Found : " + message, null, false, false);
    }
}
