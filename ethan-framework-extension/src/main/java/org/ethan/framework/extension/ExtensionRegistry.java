package org.ethan.framework.extension;

/**
 * 扩展点注册表
 * @author Ethan Zhang
 */
public interface ExtensionRegistry {

    void register(Object extension, Class<?>... extensionClazz);

}
