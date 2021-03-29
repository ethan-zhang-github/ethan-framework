package org.ethan.framework.microservices.api.gateway.filter.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Component
public class RequestMetricsFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Instant start = Instant.now();
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Method：%s\n", exchange.getRequest().getMethod()));
        builder.append(String.format("RemoteAddress：%s\n", exchange.getRequest().getRemoteAddress()));
        builder.append(String.format("Uri：%s\n", exchange.getRequest().getURI().toString()));
        builder.append("Cookies：\n");
        exchange.getRequest().getCookies().forEach((key, cookie) ->
                builder.append(cookie).append("\n"));
        builder.append("queryParams：\n");
        exchange.getRequest().getQueryParams().forEach((key, values) ->
                builder.append(key).append("=").append(String.join(", ", values)));
        log.info("Request started：\n{}", builder.toString());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Instant end = Instant.now();
            log.info("Request finished, cost {} millis", Duration.between(start, end).toMillis());
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
