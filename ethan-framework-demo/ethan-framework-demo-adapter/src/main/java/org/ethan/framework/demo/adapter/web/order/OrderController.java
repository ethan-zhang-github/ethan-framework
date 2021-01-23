package org.ethan.framework.demo.adapter.web.order;

import org.ethan.framework.demo.client.order.api.OrderService;
import org.ethan.framework.demo.client.order.dto.SearchOrderReq;
import org.ethan.framework.demo.client.order.dto.SearchOrderResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("search")
    public Flux<SearchOrderResp> search(Mono<SearchOrderReq> searchOrderReq) {
        return orderService.search(searchOrderReq);
    }

}
