package org.ethan.framework.demo.client.api;

import org.ethan.framework.demo.client.dto.CreateOrderCmd;

public interface OrderService {

    void createOrder(CreateOrderCmd createOrderCmd);

}
