package org.ethan.framework.batch.demo;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.ethan.framework.batch.listener.ChunkedMessageListener;
import org.ethan.framework.batch.listener.RocketMQListenerAdaptor;
import org.ethan.framework.batch.mongodb.document.Order;
import org.ethan.framework.batch.mongodb.repository.OrderRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@RocketMQMessageListener(
        consumerGroup = "common-consumer-group",
        topic = "common-topic",
        selectorExpression = "create-order"
)
@Component
public class OrderRocketMQMessageListener extends ChunkedMessageListener<CreateOrderMessage> implements RocketMQListenerAdaptor<CreateOrderMessage> {

    @Resource
    private OrderRepository orderRepository;

    public OrderRocketMQMessageListener(int chunk, Executor executor) {
        super(chunk, executor);
    }

    @Override
    protected void handle(List<CreateOrderMessage> messages) {
        Set<Order> orders = messages.stream().map(CreateOrderMessageConverter.INSTANCE::convert).collect(Collectors.toSet());
        orderRepository.saveAll(orders);
    }

}
