package org.ethan.framework.demo.app.order.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.ethan.framework.batch.listener.ChunkedMessageListener;
import org.ethan.framework.batch.listener.RocketMQListenerAdaptor;
import org.ethan.framework.demo.client.order.dto.CreateOrderMessage;
import org.ethan.framework.demo.app.order.converter.CreateOrderMessageConverter;
import org.ethan.framework.demo.infrastructure.gateway.impl.database.OrderRepository;
import org.ethan.framework.demo.infrastructure.gateway.impl.database.po.Order;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
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

    public OrderRocketMQMessageListener(ThreadPoolTaskExecutor taskExecutor) {
        super(10, taskExecutor);
    }

    @Override
    protected void handle(List<CreateOrderMessage> messages) {
        Set<Order> orders = messages.stream().map(CreateOrderMessageConverter.INSTANCE::convert).collect(Collectors.toSet());
        orderRepository.saveAll(orders);
    }

}
