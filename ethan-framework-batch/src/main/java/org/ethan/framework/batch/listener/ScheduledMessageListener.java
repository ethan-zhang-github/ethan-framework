package org.ethan.framework.batch.listener;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 周期性消息监听器
 * @param <T> 业务消息类型
 * @author Ethan Zhang
 */
@Getter
@Setter
public abstract class ScheduledMessageListener<T> implements MessageListener<T> {

    private long delay;
    private TimeUnit delayUnit;
    private ScheduledExecutorService scheduler;
    private TransferQueue<T> queue = new LinkedTransferQueue<>();
    private Executor executor;

    public ScheduledMessageListener(long delay, TimeUnit delayUnit, Executor executor) {
        this.delay = delay;
        this.delayUnit = delayUnit;
        this.executor = executor;
    }

    {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleWithFixedDelay(() -> {
            if (queue.isEmpty()) {
                return;
            }
            List<T> messages = new LinkedList<>();
            queue.drainTo(messages);
            CompletableFuture.runAsync(() -> handle(messages), executor);
        }, delay, delay, delayUnit);
    }

    @Override
    public void handle(T message) {
        queue.offer(message);
    }

    @Override
    public void stop() {
        scheduler.shutdown();
    }

    protected abstract void handle(List<T> messages);

}
