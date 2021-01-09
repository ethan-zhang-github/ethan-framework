package org.ethan.framework.extension;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SimpleExtensionRepository implements ExtensionRepository {

    private final ConcurrentMap<Scene, Map<Class<?>, Object>> repository = new ConcurrentHashMap<>();

    @Override
    public void save(Scene scene, Class<?> extensionClazz, Object extension) {
        Map<Class<?>, Object> classObjectMap = repository.get(scene);
        if (!repository.containsKey(scene)) {
            classObjectMap = new HashMap<>();
            repository.put(scene, classObjectMap);
        }
        classObjectMap.put(extensionClazz, extension);
    }

    @Override
    public <E> E search(Scene scene, Class<E> extensionClazz) {
        Map<Class<?>, Object> classObjectMap = repository.get(scene);
        if (Objects.nonNull(classObjectMap)) {
            Object res = classObjectMap.get(extensionClazz);
            if (extensionClazz.isAssignableFrom(res.getClass())) {
                return extensionClazz.cast(res);
            }
        }
        return null;
    }

    @Override
    public void delete(Scene scene, Class<?> extensionClazz) {
        Optional.ofNullable(repository.get(scene)).ifPresent(classObjectMap -> classObjectMap.remove(extensionClazz));
    }

}
