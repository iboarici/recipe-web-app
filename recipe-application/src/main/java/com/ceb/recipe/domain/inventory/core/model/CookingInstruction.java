package com.ceb.recipe.domain.inventory.core.model;

import lombok.*;


@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class CookingInstruction {

    /**
     * Order id of the cooking instruction Item.
     */
    private int orderId;

    /**
     * Text of the Cooking Instruction Item.
     */
    private String description;

    /**
     * Image url of the Cooking Instruction item that demonstrates how to apply instruction
     */
    private String imageUrl;

    /**
     * Video url of the Cooking Instruction item that demonstrates how to apply instruction
     */
    private String videoUrl;
}
