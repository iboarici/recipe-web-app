package com.ceb.recipe.domain.inventory.infrastructure.mapper;

import com.ceb.recipe.domain.inventory.core.model.CookingInstruction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CookingInstructionRowMapper implements RowMapper<CookingInstruction> {

    @Override
    public CookingInstruction mapRow(ResultSet rs, int rowNum) throws SQLException {
        return CookingInstruction.builder()
                .orderId((rs.getInt("ORDER_ID")))
                .description(rs.getString("DESCRIPTION"))
                .imageUrl(rs.getString("IMAGE_URL"))
                .build();
    }
}
