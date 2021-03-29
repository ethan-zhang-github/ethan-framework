package org.ethan.framework.microservices.api.gateway.security;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.UUID;

/**
 * 基于 redis 的用户认证信息存储
 */
public class RedisServerSecurityContextRepository implements ServerSecurityContextRepository {

    private static final String COOKIE_TOKEN_KEY = "ethan-token";

    private static final Duration timeout = Duration.ofDays(15);

    @Resource
    private ReactiveRedisTemplate<Object, Object> reactiveRedisTemplate;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        Object principal = context.getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) principal;
            String token = UUID.randomUUID().toString();
            exchange.getResponse().addCookie(ResponseCookie.from(COOKIE_TOKEN_KEY, token)
                    .domain("localhost").httpOnly(true).maxAge(timeout).path("/").build());
            return reactiveRedisTemplate.opsForValue().set(token, customUserDetails, timeout).then();
        }
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getCookies().getFirst(COOKIE_TOKEN_KEY))
                .flatMap(cookie -> reactiveRedisTemplate.opsForValue().get(cookie.getValue()).cast(CustomUserDetails.class)
                        .map(customUserDetails -> new SecurityContextImpl(new UsernamePasswordAuthenticationToken(customUserDetails,
                                customUserDetails.getPassword(), customUserDetails.getAuthorities()))));
    }

}
