package org.ethan.framework.demo.client.order.api;

import org.ethan.framework.demo.client.order.dto.SearchOrderReq;
import org.ethan.framework.demo.client.order.dto.SearchOrderResp;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

    Flux<SearchOrderResp> search(Mono<SearchOrderReq> searchOrderReq);

}
