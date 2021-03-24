package org.ethan.framework.microservices.api.gateway.repository;

import org.ethan.framework.microservices.api.gateway.security.CustomUserDetails;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.ethan.framework.microservices.api.gateway.security.GatewaySecurityConfiguration.PASSWORD_ENCODER;

@Repository
public class UserDetailsRepository {

    private final ConcurrentMap<String, CustomUserDetails> userDetails = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        userDetails.put("admin", new CustomUserDetails("admin", PASSWORD_ENCODER.encode("admin"), Collections.emptyList()));
        userDetails.put("user", new CustomUserDetails("user", PASSWORD_ENCODER.encode("user"), Collections.emptyList()));
        userDetails.put("subUser", new CustomUserDetails("subUser", PASSWORD_ENCODER.encode("subUser"), Collections.emptyList()));
    }

    public Mono<CustomUserDetails> getByUsername(String username) {
        return Mono.justOrEmpty(userDetails.get(username));
    }

    public Flux<CustomUserDetails> getAll() {
        return Flux.fromIterable(userDetails.values());
    }

}
