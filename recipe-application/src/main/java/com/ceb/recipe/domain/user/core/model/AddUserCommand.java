package com.ceb.recipe.domain.user.core.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddUserCommand {

    @ApiModelProperty(notes = "Username. Length must be between 5-20")
    @NotBlank(message = "username is mandatory")
    @Length(min = 5, max = 20)
    private String username;

    @ApiModelProperty(notes = "Password. Length must be between 5-20")
    @NotBlank(message = "password is mandatory")
    @Length(min = 5, max = 20)
    private String password;

    @ApiModelProperty(notes = "Role. Must be 'ADMIN' or 'USER'")
    @NotBlank(message = "role is mandatory")
    @Pattern(regexp = "ADMIN|USER", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String role;
}