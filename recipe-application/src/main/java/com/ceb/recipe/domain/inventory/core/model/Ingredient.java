package com.ceb.recipe.domain.inventory.core.model;

import lombok.*;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Ingredient {

    /**
     * Amount of the ingredient item.
     */
    private float quantity;

    /**
     * Unit of the ingredient item(Spoon, Gr, Kg).
     */
    private String unit;

    /**
     * Name of the ingredient item. Patato, Meat, Onion etc.
     */
    private String name;

    /**
     * Size of the ingredient item. Medium, Small etc.
     */
    private String size;

    /**
     * Additional info about the ingredient item
     */
    private String additionalInfo;
}
