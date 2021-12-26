package com.ceb.recipe.infrastructure;

import com.ceb.recipe.domain.user.core.AuthFacade;
import com.ceb.recipe.domain.user.core.ports.incoming.AuthenticationOperations;
import com.ceb.recipe.domain.user.core.ports.incoming.UserOperations;
import com.ceb.recipe.domain.user.core.ports.outgoing.UserDatabase;
import com.ceb.recipe.domain.user.infrastructure.UserDatabaseAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AuthDomainConfig {

    @Bean
    public UserDatabase userDatabase(JdbcTemplate jdbcTemplate, BCryptPasswordEncoder passwordEncoder) {
        return new UserDatabaseAdapter(jdbcTemplate, passwordEncoder);
    }

    @Bean
    @Qualifier("authenticationOperations")
    public AuthenticationOperations authenticationOperations(UserDatabase userDatabase) {
        return new AuthFacade(userDatabase);
    }

    @Bean
    @Qualifier("userOperations")
    public UserOperations userOperations(UserDatabase userDatabase) {
        return new AuthFacade(userDatabase);
    }
}
