package org.ethan.framework.microservices.api.gateway.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.ethan.framework.microservices.api.gateway.security.CustomUserDetails;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义权限校验过滤器，用以适配 Spring Cloud Gateway
 * @author Ethan Zhang
 */
@Slf4j
@Component
public class AuthorizationGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthorizationGatewayFilterFactory.Config> {

    @Resource
    private ServerAccessDeniedHandler accessDeniedHandler;

    public AuthorizationGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> exchange.getPrincipal()
                .filter(principal -> principal instanceof UsernamePasswordAuthenticationToken)
                .cast(UsernamePasswordAuthenticationToken.class)
                .map(UsernamePasswordAuthenticationToken::getPrincipal)
                .filter(userDetails -> userDetails instanceof CustomUserDetails)
                .cast(CustomUserDetails.class)
                .flatMap(customUserDetails -> {
                    Set<String> userAuthorities = customUserDetails.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
                    boolean matched = userAuthorities.containsAll(config.authorities);
                    return matched ? Mono.just(customUserDetails) : Mono.error(new AccessDeniedException("Authorization failed"));
                })
                .switchIfEmpty(Mono.error(new AccessDeniedException("Authorization failed")))
                .doOnSuccess(customUserDetails -> log.info("Authorization successful"))
                .doOnError(e -> accessDeniedHandler.handle(exchange, new AccessDeniedException(e.getMessage(), e)))
                .then(chain.filter(exchange));
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("authorities");
    }

    @Override
    public ShortcutType shortcutType() {
        return ShortcutType.GATHER_LIST;
    }

    @Getter
    @Setter
    public static class Config {

        private List<String> authorities = Collections.emptyList();

    }

}
