package com.ceb.recipe.domain.inventory.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Category {
    /**
     * ID of the Category
     */
    private Long id;

    /**
     * Name of the Category. Example: Breakfast, Lunch, Dinner, Snack.
     */
    private String name;
}
