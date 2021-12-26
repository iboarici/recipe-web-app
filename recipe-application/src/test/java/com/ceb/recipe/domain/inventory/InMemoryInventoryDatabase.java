package com.ceb.recipe.domain.inventory;
import com.ceb.recipe.domain.inventory.core.model.*;
import com.ceb.recipe.domain.inventory.core.ports.outgoing.InventoryDatabase;
import java.util.*;

public class InMemoryInventoryDatabase implements InventoryDatabase {

    List<Recipe> recipes;
    HashMap<String, List<Recipe>> favorites = new HashMap<>();

    public InMemoryInventoryDatabase() {
        recipes = InventoryTestData.generate();
        favorites.put("admin", recipes.subList(0, 2));
    }

    @Override
    public Optional<Recipe> findRecipeById(Long id) {
        Recipe filtered = recipes.stream().filter(recipe -> Objects.equals(recipe.getId(), id)).findAny().orElse(null);
        if (Objects.nonNull(filtered)) {
           return Optional.of(filtered);
        }
        return Optional.empty();
    }

    @Override
    public List<Ingredient> findIngredientsByRecipeId(Long id) {
        Recipe filtered = recipes.stream().filter(recipe -> Objects.equals(recipe.getId(), id)).findAny().orElse(null);
        if (Objects.nonNull(filtered) && Objects.nonNull(filtered.getIngredients())) {
            return filtered.getIngredients();
        }
        return new ArrayList<>();
    }

    @Override
    public List<CookingInstruction> findCookingInstructionsByRecipeId(Long id) {
        Recipe filtered = recipes.stream().filter(recipe -> Objects.equals(recipe.getId(), id)).findAny().orElse(null);
        if (Objects.nonNull(filtered) && Objects.nonNull(filtered.getCookingInstructions())) {
            return filtered.getCookingInstructions();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return null;
    }

    @Override
    public List<Recipe> findRecipeByCriteria(SearchCriteria searchCriteria) {
        return null;
    }

    @Override
    public boolean isRecipeExistByTitle(String title) {
        Recipe filtered = recipes.stream().filter(recipe -> Objects.equals(recipe.getTitle(), title)).findAny().orElse(null);
        return Objects.nonNull(filtered);
    }

    @Override
    public Long saveRecipe(Recipe recipe) {
        Optional<Recipe> maxIdRecipe = recipes.stream().max(Comparator.comparing(Recipe::getId));
        if (maxIdRecipe.isPresent()) {
            recipe.setId(maxIdRecipe.get().getId() + 1);
            recipes.add(recipe);
        }
        return recipe.getId();
    }

    @Override
    public void addIngredientToRecipe(Long recipeId, Ingredient ingredient) {
        Optional<Recipe> recipe = findRecipeById(recipeId);
        recipe.ifPresent(recipe1 -> recipe1.getIngredients().add(ingredient));
    }

    @Override
    public void addCookingInstructionToRecipe(Long recipeId, CookingInstruction cookingInstruction) {
        Optional<Recipe> recipe = findRecipeById(recipeId);
        recipe.ifPresent(recipe1 -> recipe1.getCookingInstructions().add(cookingInstruction));
    }

    @Override
    public void deleteRecipe(Long id) {
        Optional<Recipe> recipe = findRecipeById(id);
        recipe.ifPresent(recipes::remove);
    }

    @Override
    public void updateRecipe(Recipe recipe) {

    }

    @Override
    public boolean existsInFavorites(String username, Long recipeId) {
        List<Recipe> recipes = favorites.getOrDefault(username, new ArrayList<>());
        return Objects.nonNull(recipes.stream().filter(r -> Objects.equals(r.getId(), recipeId)).findAny().orElse(null));
    }

    @Override
    public void addToFavorites(String username, Long recipeId) {
        Optional<Recipe> recipe = findRecipeById(recipeId);
        recipe.ifPresent(recipe1 -> {
            List<Recipe> userFavorites = favorites.getOrDefault(username, new ArrayList<>());
            userFavorites.add(recipe1);
            favorites.put(username, userFavorites);
        });
    }

    @Override
    public void removeFromFavorites(String username, Long recipeId) {
        Optional<Recipe> recipe = findRecipeById(recipeId);
        recipe.ifPresent(recipe1 -> {
            List<Recipe> userFavorites = favorites.getOrDefault(username, new ArrayList<>());
            userFavorites.remove(recipe1);
            favorites.put(username, userFavorites);
        });
    }

    @Override
    public List<Recipe> getFavoriteRecipes(String username) {
        return favorites.getOrDefault(username, new ArrayList<>());
    }

    @Override
    public boolean isCategoryExistByName(String name) {
        return false;
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public void addCategory(Category category) {

    }

    @Override
    public void removeCategory(Category category) {

    }

    @Override
    public void updateCategory(Category category) {

    }
}
