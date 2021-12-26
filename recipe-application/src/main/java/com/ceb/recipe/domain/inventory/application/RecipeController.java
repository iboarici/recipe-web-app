package com.ceb.recipe.domain.inventory.application;
import com.ceb.recipe.domain.inventory.application.model.AddRecipeCommand;
import com.ceb.recipe.domain.inventory.core.model.CookingInstruction;
import com.ceb.recipe.domain.inventory.core.model.Ingredient;
import com.ceb.recipe.domain.inventory.core.model.Recipe;
import com.ceb.recipe.domain.inventory.core.model.exceptions.RecipeNotFoundException;
import com.ceb.recipe.domain.inventory.core.ports.incoming.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/recipe")
@CrossOrigin(origins = "http://localhost:4200")
@Api("Recipe Operations Controller")
public class RecipeController {

    @Qualifier("recipeOperations")
    private final RecipeOperations recipeOperations;

    @Qualifier("favoriteOperations")
    private final FavoriteOperations favoriteOperations;

    public RecipeController(RecipeOperations recipeOperations, FavoriteOperations favoriteOperations) {
        this.recipeOperations = recipeOperations;
        this.favoriteOperations = favoriteOperations;
    }

    @ApiOperation(value = "Returns all recipes in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successfully returns favorite recipes"),
            @ApiResponse(code = 404, message = "error occured while retrieving recipes")
    })
    @GetMapping("/")
    public ResponseEntity<List<Recipe>> getAllRecipes(Principal principal) {
        try {
            return new ResponseEntity<>(recipeOperations.getAllRecipes(principal.getName()), HttpStatus.OK);
        } catch (RecipeNotFoundException e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Returns recipe by its Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "returns recipe"),
            @ApiResponse(code = 404, message = "couldn't retrieve recipe by specified Id")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> findRecipeById(@PathVariable("id") Long id) {
        try {
            Recipe recipe = recipeOperations.findRecipeById(id);
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } catch (RecipeNotFoundException e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Add new recipe to the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "returns recipe"),
            @ApiResponse(code = 401, message = "authenticated user role is not admin"),
            @ApiResponse(code = 404, message = "error occured while adding new Recipe")
    })
    @PostMapping("")
    public ResponseEntity<Long> addNewRecipe(Principal principal, @Valid @RequestBody AddRecipeCommand addRecipeCommand) {
        try {
            if ("ADMIN".equalsIgnoreCase(getRole())) {
                Recipe recipe = buildRecipe(addRecipeCommand);
                recipe.setCreatedUser(principal.getName());
                Long id = recipeOperations.addNewRecipe(recipe);
                return new ResponseEntity(id, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Update Recipe in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "returns id of the recipe"),
            @ApiResponse(code = 401, message = "authenticated user role is not admin"),
            @ApiResponse(code = 404, message = "error occured while updating Recipe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Long> updateRecipe(Principal principal, @PathVariable("id") Long id, @Valid @RequestBody AddRecipeCommand addRecipeCommand) {
        try {
            if ("ADMIN".equalsIgnoreCase(getRole())) {
                Recipe existingRecipe = recipeOperations.findRecipeById(id);
                Recipe recipe = buildRecipe(addRecipeCommand);
                recipe.setId(existingRecipe.getId());
                recipe.setCreatedUser(principal.getName());
                recipeOperations.updateRecipe(recipe);
                return new ResponseEntity(id, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Delete Recipe from the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "returns if recipe deleted successfully"),
            @ApiResponse(code = 401, message = "authenticated user role is not admin"),
            @ApiResponse(code = 404, message = "error occured while deleting Recipe")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable("id") Long id) {
        try {
            if ("ADMIN".equalsIgnoreCase(getRole())) {
                recipeOperations.deleteRecipe(id);
                return new ResponseEntity<>("Recipe Deleted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @param principal Authenticated User Info. Filles Automatically by Spring Security
     * @return List of favorite recipes of the authenticated User.
     */
    @ApiOperation(value = "Returns favorite recipes of the authenticated user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successfully returns favorite recipes"),
            @ApiResponse(code = 400, message = "error occured while retrieving favorite recipes")
    })
    @GetMapping("/favorites")
    public ResponseEntity<List<Recipe>> getFavorites(Principal principal) {
        try {
            return new ResponseEntity(favoriteOperations.getFavoriteRecipes(principal.getName()), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Add recipe to authenticated user's favorites")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successfully added recipe to favorites"),
            @ApiResponse(code = 400, message = "error occured while adding recipes to favorites")
    })
    @PostMapping("/favorites/{id}")
    public ResponseEntity<Long> addToFavorites(Principal principal, @PathVariable("id") Long id) {
        try {
            favoriteOperations.addToFavorites(principal.getName(), id);
            return new ResponseEntity("Successfully added to Favorites", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Remove recipe from authenticated user's favorites")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successfully removed recipe from favorites"),
            @ApiResponse(code = 401, message = "error occured while removing recipes from favorites")
    })
    @DeleteMapping("/favorites/{id}")
    public ResponseEntity<Long> removeFromFavorites(Principal principal, @PathVariable("id") Long id) {
        try {
            favoriteOperations.removeFromFavorites(principal.getName(), id);
            return new ResponseEntity("Successfully removed from Favorites", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    private Recipe buildRecipe(AddRecipeCommand addRecipeCommand) {
        Recipe recipe = Recipe.builder().build();
        BeanUtils.copyProperties(addRecipeCommand, recipe);
        if (Objects.nonNull(addRecipeCommand.getIngredients())) {
            List<Ingredient> ingredients = new ArrayList<>();
            addRecipeCommand.getIngredients().forEach(ingredientCommand -> {
                Ingredient ingredient = Ingredient.builder().build();
                BeanUtils.copyProperties(ingredientCommand, ingredient);
                ingredients.add(ingredient);
            });
            recipe.setIngredients(ingredients);
        }
        if (Objects.nonNull(addRecipeCommand.getCookingInstructions())) {
            List<CookingInstruction> cookingInstructions = new ArrayList<>();
            addRecipeCommand.getCookingInstructions().forEach(cookingInstructionCommand -> {
                CookingInstruction cookingInstruction = CookingInstruction.builder().build();
                BeanUtils.copyProperties(cookingInstructionCommand, cookingInstruction);
                cookingInstructions.add(cookingInstruction);
            });
            recipe.setCookingInstructions(cookingInstructions);
        }
        return recipe;
    }

    private String getRole() {
        List<String > authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return authorities.isEmpty() ? null : authorities.get(0);
    }
}