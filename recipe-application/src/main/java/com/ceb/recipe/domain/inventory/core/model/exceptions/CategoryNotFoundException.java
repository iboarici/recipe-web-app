package com.ceb.recipe.domain.inventory.core.model.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) {
        super("Category Not Found : " + message);
    }
}
