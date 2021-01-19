package org.ethan.framework.batch.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 适配 spring 事件监听
 * @param <E> 事件类型
 * @author Ethan Zhang
 */
public interface ApplicationListenerAdaptor<E extends ApplicationEvent> extends MessageListener<E>, ApplicationListener<E> {

    @Override
    default void onApplicationEvent(E event) {
        handle(event);
    }

}
