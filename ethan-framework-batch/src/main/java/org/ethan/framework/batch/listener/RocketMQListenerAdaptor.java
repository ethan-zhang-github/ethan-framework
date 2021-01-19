package org.ethan.framework.batch.listener;

import org.apache.rocketmq.spring.core.RocketMQListener;

/**
 * 适配 RocketMQ 消息监听
 * @param <T> 消息类型
 * @author Ethan Zhang
 */
public interface RocketMQListenerAdaptor<T> extends MessageListener<T>, RocketMQListener<T> {

    @Override
    default void onMessage(T message) {
        handle(message);
    }

}
