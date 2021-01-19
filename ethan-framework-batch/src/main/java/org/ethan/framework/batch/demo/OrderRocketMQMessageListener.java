package org.ethan.framework.batch.demo;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.ethan.framework.batch.listener.ChunkedMessageListener;
import org.ethan.framework.batch.listener.RocketMQListenerAdaptor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Executor;

@RocketMQMessageListener(
        consumerGroup = "common-consumer-group",
        topic = "common-topic",
        selectorExpression = "create-order"
)
@Component
public class OrderRocketMQMessageListener extends ChunkedMessageListener<Order> implements RocketMQListenerAdaptor<Order> {

    @Resource
    private MongoTemplate mongoTemplate;

    public OrderRocketMQMessageListener(int chunk, Executor executor) {
        super(chunk, executor);
    }

    @Override
    protected void handle(List<Order> messages) {

    }

}
