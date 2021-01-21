package org.ethan.framework.batch.demo;

import org.ethan.framework.batch.mongodb.document.Order;
import org.ethan.framework.batch.mongodb.document.Product;
import org.springframework.core.convert.converter.Converter;

import java.math.BigInteger;
import java.util.stream.Collectors;

public enum CreateOrderMessageConverter implements Converter<CreateOrderMessage, Order> {

    INSTANCE;

    @Override
    public Order convert(CreateOrderMessage source) {
        Order order = new Order();
        order.setId(BigInteger.valueOf(source.getId()));
        order.setOrderNo(source.getOrderNo());
        order.setProducts(source.getProducts().stream().map(p -> {
            Product product = new Product();
            product.setName(p.getName());
            product.setPrice(p.getPrice());
            product.setOrder(order);
            return product;
        }).collect(Collectors.toSet()));
        return order;
    }

}
