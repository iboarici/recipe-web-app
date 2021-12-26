package com.ceb.recipe.domain.inventory.application;

import com.ceb.recipe.domain.inventory.core.model.*;
import com.ceb.recipe.domain.inventory.application.model.SearchRecipeCommand;
import com.ceb.recipe.domain.inventory.core.model.exceptions.CookingInstructionNotFoundException;
import com.ceb.recipe.domain.inventory.core.model.exceptions.IngredientNotFoundException;
import com.ceb.recipe.domain.inventory.core.model.exceptions.RecipeNotFoundException;
import com.ceb.recipe.domain.inventory.core.ports.incoming.CookingInstructionOperations;
import com.ceb.recipe.domain.inventory.core.ports.incoming.IngredientOperations;
import com.ceb.recipe.domain.inventory.core.ports.incoming.RecipeOperations;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "http://localhost:4200")
@Api("Search Operations Controller")
public class SearchController {

    @Qualifier("recipeOperations")
    private final RecipeOperations recipeOperations;

    @Qualifier("ingredientOperations")
    private final IngredientOperations ingredientOperations;

    @Qualifier("cookingInstructionOperations")
    private final CookingInstructionOperations cookingInstructionOperations;

    public SearchController(RecipeOperations recipeOperations, IngredientOperations ingredientOperations, CookingInstructionOperations cookingInstructionOperations) {
        this.recipeOperations = recipeOperations;
        this.ingredientOperations = ingredientOperations;
        this.cookingInstructionOperations = cookingInstructionOperations;
    }

    @ApiOperation(value = "Get ingredients by recipeId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "returns ingredients"),
            @ApiResponse(code = 404, message = "error occured while retrieving ingredients")
    })
    @GetMapping("/ingredients/{id}")
    public ResponseEntity<List<Ingredient>> findIngredientsById(@PathVariable("id") Long id) {
        try {
            List<Ingredient> ingredients = ingredientOperations.findIngredientByRecipeId(id);
            return new ResponseEntity<>(ingredients, HttpStatus.OK);
        } catch (IngredientNotFoundException e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Get cooking instructions by recipeId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "returns cooking instructions"),
            @ApiResponse(code = 404, message = "error occured while retrieving cooking instructions")
    })
    @GetMapping("/instructions/{id}")
    public ResponseEntity<List<CookingInstruction>> findCookingInstructionsById(@PathVariable("id") Long id) {
        try {
            List<CookingInstruction> cookingInstructions = cookingInstructionOperations.findCookingInstructionByRecipeId(id);
            return new ResponseEntity<>(cookingInstructions, HttpStatus.OK);
        } catch (CookingInstructionNotFoundException e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Find recipes by search criteria")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "returns found recipes"),
            @ApiResponse(code = 404, message = "error occured while retrieving recipes")
    })
    @PostMapping("/recipe")
    public ResponseEntity<List<Recipe>> findRecipeByCriteria(Principal principal, @Valid @RequestBody SearchRecipeCommand command) {
        try {
            List<Recipe> recipes = recipeOperations.findRecipeByCriteria(principal.getName(), buildSearchCriteria(command));
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } catch (RecipeNotFoundException e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    private SearchCriteria buildSearchCriteria(SearchRecipeCommand searchRecipeCommand) {
        SearchCriteria searchCriteria = new SearchCriteria();
        BeanUtils.copyProperties(searchRecipeCommand, searchCriteria);
        return searchCriteria;
    }
}