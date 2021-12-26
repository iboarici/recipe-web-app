package com.ceb.recipe.domain.inventory.application.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class CookingInstructionCommand {

    /**
     * Order id of the cooking instruction Item. Value must be between 1 and 50
     */
    @ApiModelProperty(notes = "Order id of the cooking instruction item. Value must be between 1 and 50")
    @NotBlank(message = "Order id is mandatory")
    @Min(1)  @Max(50)
    private int orderId;

    /**
     * Text of the Cooking Instruction Item. Length must be between 5 and 250
     */
    @ApiModelProperty(notes = "Text of the cooking instruction item. Length must be between 5 and 2500")
    @NotBlank(message = "Description is mandatory")
    @Length(min = 5, max = 2500)
    private String description;

    /**
     * Image url of the Cooking Instruction item that demonstrates how to apply instruction
     */
    @ApiModelProperty(notes = "Image url of the cooking instruction item that demonstrates how to apply instruction. Url length must be lower than 1024")
    @Length(max = 1024)
    private String imageUrl;


    /**
     * Video url of the Cooking Instruction item that demonstrates how to apply instruction
     */
    @ApiModelProperty(notes = "Video url of the cooking instruction item that demonstrates how to apply instruction. Url length must be lower than 1024")
    @Length(max = 1024)
    private String videoUrl;
}