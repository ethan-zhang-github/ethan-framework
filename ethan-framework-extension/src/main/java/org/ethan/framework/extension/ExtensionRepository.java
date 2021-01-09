package org.ethan.framework.extension;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * 扩展点仓库
 * @author Ethan Zhang
 */
public interface ExtensionRepository {

    void save(Scene scene, Class<?> extensionClazz, Object extension);

    <E> E search(Scene scene, Class<E> extensionClazz);

    void delete(Scene scene, Class<?> extensionClazz);

    default boolean exists(Scene scene, Class<?> extensionClazz) {
        return Objects.nonNull(search(scene, extensionClazz));
    }

    default void saveIfAbsent(Scene scene, Class<?> extensionClazz, Object extension) {
        if (!exists(scene, extensionClazz)) {
            save(scene, extensionClazz, extension);
        }
    }

    default <E> E searchOrElse(Scene scene, Class<E> extensionClazz, E other) {
        return Optional.ofNullable(search(scene, extensionClazz)).orElse(other);
    }

    default <E> E searchOrElseGet(Scene scene, Class<E> extensionClazz, Supplier<E> other) {
        return Optional.ofNullable(search(scene, extensionClazz)).orElseGet(other);
    }

}
