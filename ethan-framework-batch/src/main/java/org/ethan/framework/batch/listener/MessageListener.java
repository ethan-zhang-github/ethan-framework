package org.ethan.framework.batch.listener;

/**
 * 业务消息监听器
 * @param <T> 消息类型
 */
@FunctionalInterface
public interface MessageListener<T> {

    void handle(T message);

    default void stop() {}

}
