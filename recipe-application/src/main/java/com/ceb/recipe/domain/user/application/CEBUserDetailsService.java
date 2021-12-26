package com.ceb.recipe.domain.user.application;

import com.ceb.recipe.domain.user.core.model.Exception.UserNotFoundException;
import com.ceb.recipe.domain.user.core.model.User;
import com.ceb.recipe.domain.user.core.ports.incoming.AuthenticationOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Custom User Login Service
 *
 */

@Service
@RequiredArgsConstructor
public class CEBUserDetailsService implements UserDetailsService {

    private final AuthenticationOperations authenticationOperations;

    /**
     *
     * @param username username which will be checked whether it exists.
     * @return User info
     * @throws UsernameNotFoundException if username doesn't exists.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = authenticationOperations.loadUser(username);
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, getUserAuthority(user.getRole()));
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

    /**
     *
     * @param role role of the user in the database
     * @return Roles of the user. Values can be either USER or ADMIN
     */
    private List<GrantedAuthority> getUserAuthority(String role) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(role));
        return new ArrayList<>(roles);
    }
}