package com.ceb.recipe.domain.inventory.core.ports.incoming;

import com.ceb.recipe.domain.inventory.core.model.CookingInstruction;
import com.ceb.recipe.domain.inventory.core.model.exceptions.CookingInstructionNotFoundException;

import java.util.List;

public interface CookingInstructionOperations {

    List<CookingInstruction> findCookingInstructionByRecipeId(Long recipeId) throws CookingInstructionNotFoundException;
}
