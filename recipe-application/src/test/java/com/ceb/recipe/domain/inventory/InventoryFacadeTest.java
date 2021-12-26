package com.ceb.recipe.domain.inventory;
import com.ceb.recipe.domain.inventory.core.InventoryFacade;
import com.ceb.recipe.domain.inventory.core.model.CookingInstruction;
import com.ceb.recipe.domain.inventory.core.model.Ingredient;
import com.ceb.recipe.domain.inventory.core.model.Recipe;
import com.ceb.recipe.domain.inventory.core.model.exceptions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryFacadeTest {

    private InventoryFacade inventoryFacade;

    private InMemoryInventoryDatabase database;

    @BeforeEach
    void setUp() {
        database = new InMemoryInventoryDatabase();
        inventoryFacade = new InventoryFacade(database);
    }

    @AfterEach
    void tearDown() {
        database = null;
        inventoryFacade = null;
    }

    @Test
    void should_ThrowRecipeNotFoundException_WhenRecipeNotExists() {
        //Given
        Long recipeId = Long.valueOf(100);

        //When

        Executable executable = () -> inventoryFacade.findRecipeById(recipeId);

        //Then
        assertThrows(RecipeNotFoundException.class, executable);
    }

    @Test
    void should_ReturnCorrectId_WhenRecipeExists() {
        //Given
        Long recipeId = Long.valueOf(1);

        //When

        Recipe recipe = inventoryFacade.findRecipeById(recipeId);

        //Then
        assertEquals(recipeId, recipe.getId());
    }

    @Test
    void should_ThrowIngredientNotFoundException_WhenIngredientNotExistByRecipeId() {
        //Given
        Long recipeId = Long.valueOf(5);

        //When
        Executable executable = () -> inventoryFacade.findIngredientByRecipeId(recipeId);

        //Then
        assertThrows(IngredientNotFoundException.class, executable);
    }

    @Test
    void should_ReturnIngredients_WhenIngredientExistByRecipeId() {
        //Given
        Long recipeId = Long.valueOf(1);
        int expected = 20;

        //When
        List<Ingredient> ingredientList = inventoryFacade.findIngredientByRecipeId(recipeId);

        //Then
        assertEquals(expected, ingredientList.size());
    }

    @Test
    void should_ThrowCookingInstructionNotFoundException_WhenCookingInstructionNotExistByRecipeId() {
        //Given
        Long recipeId = Long.valueOf(5);

        //When
        Executable executable = () -> inventoryFacade.findCookingInstructionByRecipeId(recipeId);

        //Then
        assertThrows(CookingInstructionNotFoundException.class, executable);
    }

    @Test
    void should_ReturnCookingInstruction_WhenCookingInstructionExistByRecipeId() {
        //Given
        Long recipeId = Long.valueOf(1);
        int expected = 11;

        //When
        List<CookingInstruction> cookingInstructions = inventoryFacade.findCookingInstructionByRecipeId(recipeId);

        //Then
        assertEquals(expected, cookingInstructions.size());
    }

    @Test
    void should_ThrowRecipeDuplicateTitleException_WhenAddNewRecipeWithSameTitle() {
        //Given
        Recipe recipe = Recipe.builder().title("Kuru Fasulye").build();

        //When
        Executable executable = () -> inventoryFacade.addNewRecipe(recipe);

        //Then
        assertThrows(RecipeDuplicateTitleException.class, executable);
    }

    @Test
    void should_ReturnCorrectNexdId_WhenAddingNewRecipe() {
        //Given
        Recipe recipe = Recipe.builder().title("Musakka").calories(1000).cookingTimeInMinutes(60).prepareTimeInMinutes(60).category("Lunch").build();
        Long expectedId = 8L;

        //When
        Long id = inventoryFacade.addNewRecipe(recipe);

        //Then
        assertEquals(expectedId, id);
    }

    @Test
    void should_ThrowRecipeNotFoundException_WhenDeletingNonExistRecipe() {
        //Given
        Long recipeId = Long.valueOf(100);

        //When
        Executable executable = () -> inventoryFacade.deleteRecipe(recipeId);

        //Then
        assertThrows(RecipeNotFoundException.class, executable);
    }

    @Test
    void should_Execute_WhenDeletingExistingRecipe() {
        //Given
        Long recipeId = Long.valueOf(1);

        //When
        inventoryFacade.deleteRecipe(recipeId);

        //Then
    }

    @Test
    void should_ThrowRecipeAlreadyInFavoritesException_WhenAddingExistingRecipeToFavorites() {
        //Given
        Long recipeId = Long.valueOf(1L);

        //When
        Executable executable = () -> inventoryFacade.addToFavorites("admin", recipeId);

        //Then
        assertThrows(RecipeAlreadyInFavoritesException.class, executable);
    }

    @Test
    void should_ThrowRecipeNotFoundException_WhenAddingNotExistingRecipeToFavorites() {
        //Given
        Long recipeId = Long.valueOf(12L);

        //When
        Executable executable = () -> inventoryFacade.addToFavorites("admin", recipeId);

        //Then
        assertThrows(RecipeNotFoundException.class, executable);
    }

    @Test
    void should_AddedToFavoritesSuccessfully_WhenAddingAppropriateRecipeToFavorites() {
        //Given
        Long recipeId = Long.valueOf(3L);
        //When
        inventoryFacade.addToFavorites("admin", recipeId);

        //Then
    }

    @Test
    void should_ThrowRecipeNotInFavoritesException_WhenRecipeNotInFavoriteList() {
        //Given
        Long recipeId = Long.valueOf(13L);

        //When
        Executable executable = () -> inventoryFacade.removeFromFavorites("admin", recipeId);

        //Then
        assertThrows(RecipeNotInFavoritesException.class, executable);
    }

    @Test
    void should_RemoveFromFavorites_WhenRecipeInFavoriteList() {
        //Given
        Long recipeId = Long.valueOf(1L);
        String username = "admin";

        //When
        inventoryFacade.removeFromFavorites(username, recipeId);

        //Then
    }

    @Test
    void should_ReturnFavoriteRecipes() {
        //Given
        String username = "admin";
        int expectedSize = 2;

        //When
        List<Recipe> favoriteRecipes = inventoryFacade.getFavoriteRecipes(username);

        //Then
        assertEquals(expectedSize, favoriteRecipes.size());
    }
}