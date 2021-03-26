package org.ethan.framework.microservices.api.gateway.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.web.server.util.matcher.OrServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 基于白名单策略的匹配器
 */
@Getter
@Setter
public class WhiteListPathMatcher implements ServerWebExchangeMatcher {

    private Set<String> whiteList;

    public WhiteListPathMatcher() {
        this.whiteList = Collections.emptySet();
    }

    public WhiteListPathMatcher(Collection<String> whiteList) {
        this.whiteList = new HashSet<>(whiteList);
    }

    @Override
    public Mono<MatchResult> matches(ServerWebExchange exchange) {
        return new OrServerWebExchangeMatcher(whiteList.stream().map(PathPatternParserServerWebExchangeMatcher::new)
                .collect(Collectors.toList())).matches(exchange);
    }

}
