package org.ethan.framework.demo.app.order;

import org.ethan.framework.demo.client.order.api.OrderService;
import org.ethan.framework.demo.client.order.dto.SearchOrderReq;
import org.ethan.framework.demo.client.order.dto.SearchOrderResp;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Flux<SearchOrderResp> search(Mono<SearchOrderReq> searchOrderReq) {
        return null;
    }

}
