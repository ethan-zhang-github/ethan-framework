package org.ethan.framework.extension;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 扩展点执行器
 * @author Ethan Zhang
 */
public interface ExtensionExecutor {

    <E> E locate(Scene scene, Class<E> extensionClazz);

    default <E> void execute(Scene scene, Class<E> extensionClazz, Consumer<E> function) {
        function.accept(locate(scene, extensionClazz));
    }

    default <E, R> R execute(Scene scene, Class<E> extensionClazz, Function<E, R> function) {
        return function.apply(locate(scene, extensionClazz));
    }

    default <E> CompletableFuture<Void> executeAsync(Scene scene, Class<E> extensionClazz, Consumer<E> function, Executor executor) {
        return CompletableFuture.runAsync(() -> function.accept(locate(scene, extensionClazz)), executor);
    }

    default <E, R> CompletableFuture<R> executeAsync(Scene scene, Class<E> extensionClazz, Function<E, R> function, Executor executor) {
        return CompletableFuture.supplyAsync(() -> function.apply(locate(scene, extensionClazz)), executor);
    }

    default <E> CompletableFuture<Void> executeAsync(Scene scene, Class<E> extensionClazz, Consumer<E> function) {
        return executeAsync(scene, extensionClazz, function, ForkJoinPool.commonPool());
    }

    default <E, R> CompletableFuture<R> executeAsync(Scene scene, Class<E> extensionClazz, Function<E, R> function) {
        return executeAsync(scene, extensionClazz, function, ForkJoinPool.commonPool());
    }

}
