package com.ceb.recipe.domain.inventory.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class Recipe {

    /**
     * Id of the Recipe. Length must be between 5 and 250
     */
    private Long id;

    /**
     * Title of the Recipe.
     */
    private String title;

    /**
     * Creation Date of the Recipe. Date Format must be like 'dd-MM-yyyy HH:mm'
     */
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime creationDate;

    /**
     * Indicate that whether the recipe is vegeterian
     */
    private boolean vegetarian;

    /**
     * Indicate that recipe is for how many people
     */
    private int suitableForNumberOfPeople;

    /**
     * Recipe preparation time in minutes
     */
    private int prepareTimeInMinutes;

    /**
     * Recipe cooking time in minutes
     */
    private int cookingTimeInMinutes;

    /**
     * Indicates the total calories of the Recipe for total number of people
     */
    private int calories;

    /**
     * Video url of the recipe that demonstrates how to prepare it
     */
    private String videoUrl;

    /**
     * Image url of the recipe that shows the cooked food.
     */
    private String imageUrl;

    /**
     * Holds the username of the creator of the Recipe. Filled automatically in REST controller level.
     */
    private String createdUser;

    /**
     * Recipe category. Example: Lunch, Dinner, Breakfast, Snack
     */
    private String category;

    /**
     * List of ingredients in the Recipe.
     */
    private List<Ingredient> ingredients;

    /**
     * List of Cooking Instructions in the Recipe.
     */
    private List<CookingInstruction> cookingInstructions;

    /**
     * indicates that authenticated user added this recipe to his favorites
     */
    private boolean existInFavorites;
}