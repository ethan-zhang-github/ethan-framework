package org.ethan.framework.microservices.api.gateway.security;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
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
    private ReactiveStringRedisTemplate redisTemplate;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.fromRunnable(() -> {
            Object principal = context.getAuthentication().getPrincipal();
            if (principal instanceof CustomUserDetails) {
                CustomUserDetails customUserDetails = (CustomUserDetails) principal;
                String token = UUID.randomUUID().toString();
                redisTemplate.opsForValue().set(token, JSON.toJSONString(customUserDetails), timeout);
                exchange.getResponse().addCookie(ResponseCookie.from(COOKIE_TOKEN_KEY, token).build());
            }
        });
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getCookies().getFirst(COOKIE_TOKEN_KEY))
                .flatMap(cookie -> redisTemplate.opsForValue().get(cookie.getValue()).map(cache -> JSON.parseObject(cache, CustomUserDetails.class))
                        .map(customUserDetails -> new SecurityContextImpl(new UsernamePasswordAuthenticationToken(customUserDetails,
                                customUserDetails.getPassword(), customUserDetails.getAuthorities()))));
    }

}
