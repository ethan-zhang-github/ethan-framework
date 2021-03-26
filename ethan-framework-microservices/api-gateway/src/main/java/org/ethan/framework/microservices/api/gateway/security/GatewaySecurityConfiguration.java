package org.ethan.framework.microservices.api.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;

@EnableWebFluxSecurity
public class GatewaySecurityConfiguration {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private static final String FROM_LOGIN_URL = "/security/login";

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        return new CustomReactiveUserDetailsService();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange().anyExchange().authenticated().and()
                .formLogin()
                .requiresAuthenticationMatcher(new PathPatternParserServerWebExchangeMatcher(FROM_LOGIN_URL))
                .authenticationSuccessHandler(customAuthenticationSuccessHandler())
                .authenticationFailureHandler(customAuthenticationFailureHandler())
                .securityContextRepository(redisServerSecurityContextRepository()).and()
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint())
                .accessDeniedHandler(customAccessDeniedHandler()).and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PASSWORD_ENCODER;
    }

    @Bean
    public CustomAuthenticationEntryPoint customAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public RedisServerSecurityContextRepository redisServerSecurityContextRepository() {
        return new RedisServerSecurityContextRepository();
    }

}
