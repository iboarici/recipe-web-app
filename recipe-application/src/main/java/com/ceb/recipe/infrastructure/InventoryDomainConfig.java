package com.ceb.recipe.infrastructure;

import com.ceb.recipe.domain.inventory.core.InventoryFacade;
import com.ceb.recipe.domain.inventory.core.ports.incoming.*;
import com.ceb.recipe.domain.inventory.core.ports.outgoing.InventoryDatabase;
import com.ceb.recipe.domain.inventory.infrastructure.InventoryDatabaseAdapter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class InventoryDomainConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        return objectMapper;
    }

    @Bean
    public InventoryDatabase searchDatabase(JdbcTemplate jdbcTemplate) {
        return new InventoryDatabaseAdapter(jdbcTemplate);
    }

    @Bean
    @Qualifier("recipeOperations")
    public RecipeOperations recipeOperations(InventoryDatabase inventoryDatabase) {
        return new InventoryFacade(inventoryDatabase);
    }

    @Bean
    @Qualifier("categoryOperations")
    public CategoryOperations categoryOperations(InventoryDatabase inventoryDatabase) {
        return new InventoryFacade(inventoryDatabase);
    }

    @Bean
    @Qualifier("ingredientOperations")
    public IngredientOperations ingredientOperations(InventoryDatabase inventoryDatabase) {
        return new InventoryFacade(inventoryDatabase);
    }


    @Bean
    @Qualifier("cookingInstructionOperations")
    public CookingInstructionOperations cookingInstructionOperations(InventoryDatabase inventoryDatabase) {
        return new InventoryFacade(inventoryDatabase);
    }

    @Bean
    @Qualifier("favoriteOperations")
    public FavoriteOperations favoriteOperations(InventoryDatabase inventoryDatabase) {
        return new InventoryFacade(inventoryDatabase);
    }
}
