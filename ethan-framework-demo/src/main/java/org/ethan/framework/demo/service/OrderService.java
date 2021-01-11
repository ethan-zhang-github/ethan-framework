package org.ethan.framework.demo.service;

import org.ethan.framework.demo.dto.CreateOrderCmd;

public interface OrderService {

    void createOrder(CreateOrderCmd createOrderCmd);

}
