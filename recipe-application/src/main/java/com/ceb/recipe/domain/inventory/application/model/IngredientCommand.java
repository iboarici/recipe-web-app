package com.ceb.recipe.domain.inventory.application.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class IngredientCommand {

    /**
     * Amount of the ingredient item. Value must be between 1 and 50
     */
    @ApiModelProperty(notes = "Amount of the ingredient item. Value must be between 1 and 50")
    @DecimalMin("0.1") @DecimalMax("30.00")
    private float quantity;

    /**
     * Unit of the ingredient item(Spoon, Gr, Kg).
     */
    @ApiModelProperty(notes = "Unit of the ingredient item(Spoon, Gr, Kg).")
    private String unit;

    /**
     * Name of the ingredient item. Patato, Meat, Onion etc.
     */
    @ApiModelProperty(notes = "Name of the ingredient item. Patato, Meat, Onion etc.")
    private String name;

    /**
     * Size of the ingredient item. Medium, Small etc.
     */
    @ApiModelProperty(notes = "Size of the ingredient item. Medium, Small etc.")
    private String size;

    /**
     * Additional info about the ingredient item
     */
    @ApiModelProperty(notes = "Additional info about the ingredient item")
    private String additionalInfo;
}
