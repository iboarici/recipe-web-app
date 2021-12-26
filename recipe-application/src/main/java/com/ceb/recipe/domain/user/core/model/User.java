package com.ceb.recipe.domain.user.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class User {
    private String username;
    private String password;
    private String role;
}
