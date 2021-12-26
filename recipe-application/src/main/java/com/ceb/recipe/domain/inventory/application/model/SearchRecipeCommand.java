package com.ceb.recipe.domain.inventory.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class SearchRecipeCommand {

    /**
     * Title of the Recipe. Length must be between 5 and 250
     */
    @ApiModelProperty(notes = "Title of the Recipe")
    @Length(min = 5, max = 250)
    private String title;

    @ApiModelProperty(notes = "Creation Date Start Value. Date Format must be like 'dd-MM-yyyy HH:mm'")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createDateSTART;

    @ApiModelProperty(notes = "Creation Date End Value. Date Format must be like 'dd-MM-yyyy HH:mm'")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createDateEND;

    /**
     * Indicate that whether the recipe is vegeterian
     */
    @ApiModelProperty(notes = "Indicate that whether the recipe is suitable for vegeterian people")
    private Boolean vegetarian;

    @ApiModelProperty(notes = "SuitableForNumberOfPeople is equal to this value")
    @Min(1)
    @Max(10)
    private Integer suitableForNumberOfPeopleEQUAL;

    @ApiModelProperty(notes = "SuitableForNumberOfPeople is greater than this value")
    @Min(1)
    @Max(10)
    private Integer suitableForNumberOfPeopleGT;

    @ApiModelProperty(notes = "SuitableForNumberOfPeople is greater than equal this value")
    @Min(0)
    @Max(10)
    private Integer suitableForNumberOfPeopleGTE;

    @Min(0)
    @Max(10)
    @ApiModelProperty(notes = "SuitableForNumberOfPeople is less than this value")
    private Integer suitableForNumberOfPeopleLT;
    @Min(0)
    @Max(10)
    @ApiModelProperty(notes = "SuitableForNumberOfPeople is less than equal this value")
    private Integer suitableForNumberOfPeopleLTE;

    @Min(5)
    @Max(300)
    @ApiModelProperty(notes = "Prepare Time is equal to this value")
    private Integer prepareTimeInMinutesEQUAL;
    @Min(5)
    @Max(300)
    @ApiModelProperty(notes = "Prepare Time is greater than this value")
    private Integer prepareTimeInMinutesGT;
    @Min(5)
    @Max(300)
    @ApiModelProperty(notes = "Prepare Time is greater than equal this value")
    private Integer prepareTimeInMinutesGTE;
    @Min(5)
    @Max(300)
    @ApiModelProperty(notes = "Prepare Time is less than this value")
    private Integer prepareTimeInMinutesLT;
    @Min(5)
    @Max(300)
    @ApiModelProperty(notes = "Prepare Time is less than equal this value")
    private Integer prepareTimeInMinutesLTE;
    @Min(5)
    @Max(300)
    @ApiModelProperty(notes = "Cooking Time is equal to this value")
    private Integer cookingTimeInMinutesEQUAL;

    @Min(5)
    @Max(300)
    @ApiModelProperty(notes = "Cooking Time is greater than this value")
    private Integer cookingTimeInMinutesGT;
    @Min(5)
    @Max(300)
    @ApiModelProperty(notes = "Cooking Time is greater than equal this value")
    private Integer cookingTimeInMinutesGTE;
    @Min(5)
    @Max(300)
    @ApiModelProperty(notes = "Cooking Time is less than this value")
    private Integer cookingTimeInMinutesLT;
    @Min(5)
    @Max(300)
    @ApiModelProperty(notes = "Cooking Time is less than equal this value")
    private Integer cookingTimeInMinutesLTE;

    @Min(0)
    @Max(10000)
    @ApiModelProperty(notes = "Calories is equal to this value")
    private Integer caloriesEQUAL;
    @Min(0)
    @Max(10000)
    @ApiModelProperty(notes = "Calories is greater than this value")
    private Integer caloriesGT;
    @Min(0)
    @Max(10000)
    @ApiModelProperty(notes = "Calories is greater than equal this value")
    private Integer caloriesGTE;
    @Min(0)
    @Max(10000)
    @ApiModelProperty(notes = "Calories is less than this value")
    private Integer caloriesLT;
    @Min(0)
    @Max(10000)
    @ApiModelProperty(notes = "Calories is less than equal this value")
    private Integer caloriesLTE;

    @ApiModelProperty(notes = "Indicates that whether the result should include ingredients")
    private Boolean includeIngredients;
    @ApiModelProperty(notes = "Indicates that whether the result should include cooking instructions")
    private Boolean includeCookingInstructions;
    @ApiModelProperty(notes = "List of users")
    private List<String> users;

}