package org.ethan.framework.demo.controller;

import org.ethan.framework.demo.dto.CreateOrderCmd;
import org.ethan.framework.demo.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("create")
    public void create(@RequestBody CreateOrderCmd createOrderCmd) {
        orderService.createOrder(createOrderCmd);
    }

}
