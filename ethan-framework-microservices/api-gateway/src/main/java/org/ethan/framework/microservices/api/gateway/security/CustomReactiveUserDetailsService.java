package org.ethan.framework.microservices.api.gateway.security;

import org.ethan.framework.microservices.api.gateway.repository.UserDetailsRepository;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

public class CustomReactiveUserDetailsService implements ReactiveUserDetailsService {

    @Resource
    private UserDetailsRepository userDetailsRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userDetailsRepository.getByUsername(username).cast(UserDetails.class);
    }

}
