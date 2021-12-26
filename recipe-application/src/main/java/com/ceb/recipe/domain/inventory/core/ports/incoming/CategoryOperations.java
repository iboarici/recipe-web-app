package com.ceb.recipe.domain.inventory.core.ports.incoming;

import com.ceb.recipe.domain.inventory.core.model.Category;
import com.ceb.recipe.domain.inventory.core.model.exceptions.CategoryAlreadyExistException;
import com.ceb.recipe.domain.inventory.core.model.exceptions.CategoryNotFoundException;

import java.util.List;

public interface CategoryOperations {

    List<Category> getAll();

    void addCategory(Category category) throws CategoryAlreadyExistException;

    void removeCategory(Category category) throws CategoryNotFoundException;

    void updateCategory(Category category) throws CategoryNotFoundException;
}
