package com.ceb.recipe.domain.inventory.core;

import com.ceb.recipe.domain.inventory.core.model.*;
import com.ceb.recipe.domain.inventory.core.model.exceptions.*;
import com.ceb.recipe.domain.inventory.core.ports.incoming.*;
import com.ceb.recipe.domain.inventory.core.ports.outgoing.InventoryDatabase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class InventoryFacade implements CategoryOperations, RecipeOperations, IngredientOperations, CookingInstructionOperations, FavoriteOperations {

    private final InventoryDatabase inventoryDatabase;

    @Override
    public List<Recipe> getAllRecipes(String username) {
        List<Recipe> recipes = inventoryDatabase.getAllRecipes();
        recipes.forEach(recipe -> recipe.setExistInFavorites(inventoryDatabase.existsInFavorites(username, recipe.getId())));
        return recipes;
    }

    @Override
    public Recipe findRecipeById(Long id) throws RecipeNotFoundException {
        return inventoryDatabase.findRecipeById(id).orElseThrow(() -> new RecipeNotFoundException(id.toString()));
    }

    @Override
    public List<Recipe> findRecipeByCriteria(String username, SearchCriteria searchCriteria)  throws RecipeNotFoundException {
        List<Recipe> recipes = inventoryDatabase.findRecipeByCriteria(searchCriteria);
        recipes.forEach(recipe -> recipe.setExistInFavorites(inventoryDatabase.existsInFavorites(username, recipe.getId())));
        return recipes;
    }

    @Override
    public List<Ingredient> findIngredientByRecipeId(Long recipeId) throws IngredientNotFoundException {
        List<Ingredient> ingredients =  inventoryDatabase.findIngredientsByRecipeId(recipeId);
        if (ingredients.isEmpty()) {
            throw new IngredientNotFoundException(recipeId.toString());
        }
        return ingredients;
    }

    @Override
    public List<CookingInstruction> findCookingInstructionByRecipeId(Long recipeId) throws CookingInstructionNotFoundException {
        List<CookingInstruction> cookingInstructions = inventoryDatabase.findCookingInstructionsByRecipeId(recipeId);
        if (cookingInstructions.isEmpty()) {
            throw new CookingInstructionNotFoundException(recipeId.toString());
        }
        return cookingInstructions;
    }

    @Override
    public Long addNewRecipe(Recipe recipe) throws RecipeDuplicateTitleException {
        if (inventoryDatabase.isRecipeExistByTitle(recipe.getTitle())) {
            throw new RecipeDuplicateTitleException(recipe.getTitle());
        }
        Long recipeId =  inventoryDatabase.saveRecipe(recipe);
        if (Objects.nonNull(recipe.getIngredients())) {
            recipe.getIngredients().forEach(ingredient -> inventoryDatabase.addIngredientToRecipe(recipeId, ingredient));
        }
        if (Objects.nonNull(recipe.getCookingInstructions())) {
            recipe.getCookingInstructions().forEach(cookingInstruction -> inventoryDatabase.addCookingInstructionToRecipe(recipeId, cookingInstruction));
        }
        return recipeId;
    }

    @Override
    public void deleteRecipe(Long recipeId) throws RecipeNotFoundException {
        if (!inventoryDatabase.findRecipeById(recipeId).isPresent()) {
            throw new RecipeNotFoundException(recipeId.toString());
        }
        inventoryDatabase.deleteRecipe(recipeId);
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        inventoryDatabase.updateRecipe(recipe);
    }

    @Override
    public void addToFavorites(String username, Long recipeId) throws RecipeAlreadyInFavoritesException, RecipeNotFoundException {
        if (inventoryDatabase.existsInFavorites(username, recipeId)) {
            throw new RecipeAlreadyInFavoritesException(username, recipeId);
        }
        inventoryDatabase.findRecipeById(recipeId).orElseThrow(() -> new RecipeNotFoundException(recipeId.toString()));
        inventoryDatabase.addToFavorites(username, recipeId);
    }

    @Override
    public void removeFromFavorites(String username, Long recipeId) throws RecipeNotInFavoritesException {
        if (!inventoryDatabase.existsInFavorites(username, recipeId)) {
            throw new RecipeNotInFavoritesException(username, recipeId);
        }
        inventoryDatabase.removeFromFavorites(username, recipeId);
    }

    @Override
    public List<Recipe> getFavoriteRecipes(String username) {
        return inventoryDatabase.getFavoriteRecipes(username);
    }

    @Override
    public List<Category> getAll() {
        return inventoryDatabase.getAllCategories();
    }

    @Override
    public void addCategory(Category category) throws CategoryAlreadyExistException {
        if (inventoryDatabase.isCategoryExistByName(category.getName())) {
            throw new CategoryAlreadyExistException(category.getName());
        }
        inventoryDatabase.addCategory(category);
    }

    @Override
    public void removeCategory(Category category) throws CategoryNotFoundException {
        if (!inventoryDatabase.isCategoryExistByName(category.getName())) {
            throw new CategoryNotFoundException(category.getName());
        }
        inventoryDatabase.removeCategory(category);
    }

    @Override
    public void updateCategory(Category category) throws CategoryNotFoundException {
        if (!inventoryDatabase.isCategoryExistByName(category.getName())) {
            throw new CategoryNotFoundException(category.getName());
        }
        inventoryDatabase.updateCategory(category);
    }
}