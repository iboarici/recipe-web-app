package com.ceb.recipe.domain.inventory.it;

import com.ceb.recipe.domain.inventory.core.model.CookingInstruction;
import com.ceb.recipe.domain.inventory.core.model.Ingredient;
import com.ceb.recipe.domain.inventory.core.model.Recipe;
import com.ceb.recipe.domain.inventory.core.model.exceptions.*;
import com.ceb.recipe.domain.inventory.core.ports.incoming.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class InventoryIntegrationTests {

    @Autowired
    @Qualifier("recipeOperations")
    private RecipeOperations recipeOperations;

    @Autowired
    @Qualifier("favoriteOperations")
    private FavoriteOperations favoriteOperations;

    @Autowired
    @Qualifier("ingredientOperations")
    private IngredientOperations ingredientOperations;

    @Autowired
    @Qualifier("cookingInstructionOperations")
    private CookingInstructionOperations cookingInstructionOperations;

    @Test
    void should_ThrowRecipeNotFoundException_WhenRecipeNotExists() {
        //Given
        Long recipeId = Long.valueOf(100);

        //When

        Executable executable = () -> recipeOperations.findRecipeById(recipeId);

        //Then
        assertThrows(RecipeNotFoundException.class, executable);
    }

    @Test
    void should_ReturnCorrectId_WhenRecipeExists() {
        //Given
        Long recipeId = Long.valueOf(1);

        //When

        Recipe recipe = recipeOperations.findRecipeById(recipeId);

        //Then
        assertEquals(recipeId, recipe.getId());
    }

    @Test
    void should_ThrowIngredientNotFoundException_WhenIngredientNotExistByRecipeId() {
        //Given
        Long recipeId = Long.valueOf(50);

        //When
        Executable executable = () -> ingredientOperations.findIngredientByRecipeId(recipeId);

        //Then
        assertThrows(IngredientNotFoundException.class, executable);
    }

    @Test
    void should_ReturnIngredients_WhenIngredientExistByRecipeId() {
        //Given
        Long recipeId = Long.valueOf(1);
        int expected = 20;

        //When
        List<Ingredient> ingredientList = ingredientOperations.findIngredientByRecipeId(recipeId);

        //Then
        assertEquals(expected, ingredientList.size());
    }

    @Test
    void should_ThrowCookingInstructionNotFoundException_WhenCookingInstructionNotExistByRecipeId() {
        //Given
        Long recipeId = Long.valueOf(50L);

        //When
        Executable executable = () -> cookingInstructionOperations.findCookingInstructionByRecipeId(recipeId);

        //Then
        assertThrows(CookingInstructionNotFoundException.class, executable);
    }

    @Test
    void should_ReturnCookingInstruction_WhenCookingInstructionExistByRecipeId() {
        //Given
        Long recipeId = Long.valueOf(1);
        int expected = 11;

        //When
        List<CookingInstruction> cookingInstructions = cookingInstructionOperations.findCookingInstructionByRecipeId(recipeId);

        //Then
        assertEquals(expected, cookingInstructions.size());
    }

    @Test
    void should_ThrowRecipeDuplicateTitleException_WhenAddNewRecipeWithSameTitle() {
        //Given
        Recipe recipe = Recipe.builder().title("Kuru Fasulye").build();

        //When
        Executable executable = () -> recipeOperations.addNewRecipe(recipe);

        //Then
        assertThrows(RecipeDuplicateTitleException.class, executable);
    }

    @Test
    void should_ReturnCorrectNexdId_WhenAddingNewRecipe() {
        Recipe recipe = Recipe.builder().title("Musakka").calories(1000).cookingTimeInMinutes(60).prepareTimeInMinutes(60).category("Lunch").creationDate(LocalDateTime.now())
                .cookingInstructions(Arrays.asList(CookingInstruction.builder().description("Sample").orderId(1).build()))
                .ingredients(Arrays.asList(Ingredient.builder().name("Sample Ingredient").quantity(1.0F).size("Middle").unit("Kg").build()))
                .build();
        Long expectedId = 8L;

        //When
        Long id = recipeOperations.addNewRecipe(recipe);

        //Then
        assertEquals(expectedId, id);
    }

    @Test
    void should_ThrowRecipeNotFoundException_WhenDeletingNonExistRecipe() {
        //Given
        Long recipeId = Long.valueOf(100);

        //When
        Executable executable = () -> recipeOperations.deleteRecipe(recipeId);

        //Then
        assertThrows(RecipeNotFoundException.class, executable);
    }

    @Test
    void should_Execute_WhenDeletingExistingRecipe() {
        //Given
        Long recipeId = Long.valueOf(7);

        //When
        recipeOperations.deleteRecipe(recipeId);

        //Then
    }


    @Test
    void should_ThrowRecipeNotFoundException_WhenAddingNotExistingRecipeToFavorites() {
        //Given
        Long recipeId = Long.valueOf(12L);

        //When
        Executable executable = () -> favoriteOperations.addToFavorites("admin", recipeId);

        //Then
        assertThrows(RecipeNotFoundException.class, executable);
    }

    @Test
    void should_AddedToFavoritesSuccessfully_WhenAddingAppropriateRecipeToFavorites() {
        //Given
        Long recipeId = Long.valueOf(3L);
        //When
        favoriteOperations.addToFavorites("admin", recipeId);

        //Then
    }

    @Test
    void should_ThrowRecipeAlreadyInFavoritesException_WhenAddingExistingRecipeToFavorites() {
        //Given
        Long recipeId = Long.valueOf(3L);

        //When
        Executable executable = () -> favoriteOperations.addToFavorites("admin", recipeId);

        //Then
        assertThrows(RecipeAlreadyInFavoritesException.class, executable);
    }

    @Test
    void should_ThrowRecipeNotInFavoritesException_WhenRecipeNotInFavoriteList() {
        //Given
        Long recipeId = Long.valueOf(13L);

        //When
        Executable executable = () -> favoriteOperations.removeFromFavorites("admin", recipeId);

        //Then
        assertThrows(RecipeNotInFavoritesException.class, executable);
    }

    @Test
    void should_RemoveFromFavorites_WhenRecipeInFavoriteList() {
        //Given
        Long recipeId = Long.valueOf(3L);
        String username = "admin";

        //When
        favoriteOperations.removeFromFavorites(username, recipeId);

        //Then
    }

    @Test
    void should_ReturnFavoriteRecipes() {
        //Given
        String username = "admin";
        int expectedSize = 1;

        //When
        List<Recipe> favoriteRecipes = favoriteOperations.getFavoriteRecipes(username);

        //Then
        assertEquals(expectedSize, favoriteRecipes.size());
    }
}
