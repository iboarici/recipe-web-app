package com.ceb.recipe.domain.inventory.core.ports.incoming;

import com.ceb.recipe.domain.inventory.core.model.Ingredient;
import com.ceb.recipe.domain.inventory.core.model.exceptions.IngredientNotFoundException;

import java.util.List;

public interface IngredientOperations {

    List<Ingredient> findIngredientByRecipeId(Long recipeId) throws IngredientNotFoundException;
}
