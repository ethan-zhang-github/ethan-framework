package org.ethan.framework.microservices.api.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * 安全配置
 * @author Ethan Zhang
 */
@EnableWebFluxSecurity
public class GatewaySecurityConfiguration {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private static final String LOGIN_URL = "/security/login";
    private static final String LOGOUT_URL = "/security/logout";

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        return new CustomReactiveUserDetailsService();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                // 跨域处理策略
                .cors().configurationSource(corsConfigurationSource())
                .and()
                // 禁用 CSRF
                .csrf().disable()
                // 身份上下文存储
                .securityContextRepository(redisServerSecurityContextRepository())
                // 身份认证
                .authorizeExchange().anyExchange().authenticated()
                .and()
                // 表单登录
                .formLogin().loginPage(LOGIN_URL)
                .authenticationSuccessHandler(customAuthenticationSuccessHandler())
                .authenticationFailureHandler(customAuthenticationFailureHandler())
                .and()
                // 匿名用户
                .anonymous()
                .and()
                // 退出
                .logout().logoutUrl(LOGOUT_URL).logoutSuccessHandler(customLogoutSuccessHandler())
                .and()
                // 异常处理
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint())
                .accessDeniedHandler(customAccessDeniedHandler())
                .and()
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

    @Bean
    public CustomLogoutSuccessHandler customLogoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return exchange -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowedOrigins(Collections.singletonList("localhost"));
            corsConfiguration.setAllowedMethods(Arrays.stream(HttpMethod.values())
                    .map(HttpMethod::toString).collect(Collectors.toList()));
            return corsConfiguration;
        };
    }

}
