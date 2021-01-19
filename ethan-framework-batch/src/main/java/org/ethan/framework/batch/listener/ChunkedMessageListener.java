package org.ethan.framework.batch.listener;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * chunk oriented 块驱动
 * @param <T> 业务消息类型
 * @author Ethan Zhang
 */
@Getter
@Setter
public abstract class ChunkedMessageListener<T> implements MessageListener<T> {

    private int chunk;
    private Executor executor;
    private TransferQueue<T> queue = new LinkedTransferQueue<>();
    private boolean stopped;

    public ChunkedMessageListener(int chunk, Executor executor) {
        this.chunk = chunk;
        this.executor = executor;
    }

    @Override
    public void handle(T message) {
        if (stopped) {
            return;
        }
        queue.offer(message);
        if (queue.size() >= chunk) {
            pullAndHandle(chunk);
        }
    }

    @Override
    public void stop() {
        this.stopped = true;
        pullAndHandle(Integer.MAX_VALUE);
    }

    private void pullAndHandle(int maxElements) {
        List<T> messages = new LinkedList<>();
        queue.drainTo(messages, maxElements);
        CompletableFuture.runAsync(() -> handle(messages), executor);
    }

    protected abstract void handle(List<T> messages);

}
