package com.ceb.recipe.domain.inventory.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddRecipeCommand {

    /**
     * Title of the Recipe. Length must be between 5 and 250
     */
    @ApiModelProperty(notes = "Title of the Recipe. Length must be between 5 and 250")
    @NotBlank(message = "title is mandatory")
    @Length(min = 5, max = 250)
    private String title;

    /**
     * Creation Date of the Recipe. Date Format must be like 'dd-MM-yyyy HH:mm'
     */
    @ApiModelProperty(notes = "Creation Date of the Recipe. Date Format must be like 'dd-MM-yyyy HH:mm'")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime creationDate;

    /**
     * Indicate that whether the recipe is vegeterian
     */
    @ApiModelProperty(notes = "Indicate that whether the recipe is suitable for vegeterian people")
    private boolean vegetarian;

    /**
     * Indicate that recipe is for how many people
     */
    @ApiModelProperty(notes = "Indicate that recipe is for how many people. Range must be between 1-10")
    @Min(1)  @Max(10)
    private int suitableForNumberOfPeople;

    /**
     * Recipe preparation time in minutes
     */
    @ApiModelProperty(notes = "Recipe preparation time in minutes. Range must be between 5-300")
    @Min(5)
    @Max(300)
    private int prepareTimeInMinutes;

    /**
     * Recipe cooking time in minutes
     */
    @ApiModelProperty(notes = "Cooking time in minutes. Range must be between 5-300")
    @Min(5)
    @Max(300)
    private int cookingTimeInMinutes;

    /**
     * Indicates the total calories of the Recipe for total number of people
     */
    @ApiModelProperty(notes = "Total calories of the Recipe. Must be lower than 10000")
    @Min(0)
    @Max(10000)
    private int calories;

    /**
     * Video url of the recipe that demonstrates how to prepare it
     */
    @ApiModelProperty(notes = "Video url of the recipe that demonstrates how to prepare it. Url length must be lower than 1024")
    @Length(max = 1024)
    private String videoUrl;

    /**
     * Image url of the recipe that shows the cooked food.
     */
    @ApiModelProperty(notes = "Image url of the recipe that shows the cooked food.. Url length must be lower than 1024")
    @Length(max = 1024)
    private String imageUrl;

    /**
     * Recipe category. Example: Lunch, Dinner, Breakfast, Snack
     */
    @ApiModelProperty(notes = "Recipe category. Example: 'Lunch', 'Dinner', 'Breakfast', 'Snack'. length must be between 5-100")
    @Length(min = 5, max = 100)
    private String category;

    /**
     * List of ingredients in the Recipe.
     */
    @ApiModelProperty(notes = "List of ingredients in the Recipe.")
    private List<IngredientCommand> ingredients;

    /**
     * List of Cooking Instructions in the Recipe.
     */
    @ApiModelProperty(notes = "List of cooking instructions in the Recipe.")
    private List<CookingInstructionCommand> cookingInstructions;
}
