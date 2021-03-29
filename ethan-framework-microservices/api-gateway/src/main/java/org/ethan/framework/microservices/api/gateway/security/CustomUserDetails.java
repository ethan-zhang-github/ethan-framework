package org.ethan.framework.microservices.api.gateway.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {

    public CustomUserDetails(String username, String password, Collection<SimpleGrantedAuthority> authorities) {
        super(username, password, authorities);
    }

}
