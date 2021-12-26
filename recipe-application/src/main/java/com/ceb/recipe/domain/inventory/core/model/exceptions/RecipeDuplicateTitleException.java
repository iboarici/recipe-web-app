package com.ceb.recipe.domain.inventory.core.model.exceptions;

public class RecipeDuplicateTitleException extends RuntimeException{

    public RecipeDuplicateTitleException(String message) {
        super("Recipe Duplicate Title : " + message, null, false, false);
    }
}
