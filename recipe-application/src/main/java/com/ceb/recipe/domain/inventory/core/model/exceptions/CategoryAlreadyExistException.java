package com.ceb.recipe.domain.inventory.core.model.exceptions;

public class CategoryAlreadyExistException extends RuntimeException {

    public CategoryAlreadyExistException(String message) {
        super("Category Already Exist : " + message);
    }

}
