package org.ethan.framework.microservices.api.gateway.security;

import com.alibaba.fastjson.JSON;
import org.ethan.framework.microservices.api.gateway.vo.Response;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class CustomAccessDeniedHandler implements ServerAccessDeniedHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        return Mono.fromRunnable(() -> {
            ServerHttpResponse response = exchange.getResponse();
            response.writeWith(Mono.just(response.bufferFactory().wrap(JSON.toJSONBytes(Response.unauthorized()))));
            response.setComplete();
        });
    }

}
