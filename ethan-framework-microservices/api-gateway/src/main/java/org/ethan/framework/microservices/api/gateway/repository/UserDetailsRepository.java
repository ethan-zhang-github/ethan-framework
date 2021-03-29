package org.ethan.framework.microservices.api.gateway.repository;

import org.ethan.framework.microservices.api.gateway.security.CustomUserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.ethan.framework.microservices.api.gateway.security.GatewaySecurityConfiguration.PASSWORD_ENCODER;

@Repository
public class UserDetailsRepository {

    private final ConcurrentMap<String, CustomUserDetails> userDetails = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        userDetails.put("admin", new CustomUserDetails("admin", PASSWORD_ENCODER.encode("admin"), Arrays.asList(
                new SimpleGrantedAuthority("createOrder"),
                new SimpleGrantedAuthority("modifyOrder"),
                new SimpleGrantedAuthority("deleteOrder"),
                new SimpleGrantedAuthority("deleteOrder")
        )));
        userDetails.put("user", new CustomUserDetails("user", PASSWORD_ENCODER.encode("user"), Arrays.asList(
                new SimpleGrantedAuthority("createOrder"),
                new SimpleGrantedAuthority("modifyOrder"),
                new SimpleGrantedAuthority("queryOrder")
        )));
        userDetails.put("subUser", new CustomUserDetails("subUser", PASSWORD_ENCODER.encode("subUser"), Arrays.asList(
                new SimpleGrantedAuthority("createOrder"),
                new SimpleGrantedAuthority("queryOrder")
        )));
    }

    public Mono<CustomUserDetails> getByUsername(String username) {
        return Mono.justOrEmpty(userDetails.get(username));
    }

    public Flux<CustomUserDetails> getAll() {
        return Flux.fromIterable(userDetails.values());
    }

}
