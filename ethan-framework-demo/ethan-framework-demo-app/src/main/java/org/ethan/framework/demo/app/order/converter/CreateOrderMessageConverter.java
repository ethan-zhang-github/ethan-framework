package org.ethan.framework.demo.app.order.converter;

import org.ethan.framework.demo.client.order.dto.CreateOrderMessage;
import org.ethan.framework.demo.infrastructure.gateway.impl.database.model.Order;
import org.ethan.framework.demo.infrastructure.gateway.impl.database.model.Product;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public enum CreateOrderMessageConverter implements Converter<CreateOrderMessage, Order> {

    INSTANCE;

    @Override
    public Order convert(CreateOrderMessage source) {
        Order order = new Order();
        order.setId(source.getId());
        order.setOrderNo(source.getOrderNo());
        return order;
    }

}
