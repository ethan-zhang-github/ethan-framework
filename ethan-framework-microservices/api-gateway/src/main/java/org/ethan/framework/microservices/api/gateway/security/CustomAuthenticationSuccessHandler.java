package org.ethan.framework.microservices.api.gateway.security;

import com.alibaba.fastjson.JSON;
import org.ethan.framework.microservices.api.gateway.vo.Response;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import reactor.core.publisher.Mono;

public class CustomAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        return Mono.fromRunnable(() -> {
            ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
            response.writeWith(Mono.just(response.bufferFactory().wrap(JSON.toJSONBytes(Response.success("登录授权成功")))));
            response.setComplete();
        });
    }

}
