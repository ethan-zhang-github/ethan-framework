package org.ethan.framework.extension;

import java.util.Collection;

/**
 * 业务扩展点
 * @author Ethan Zhang
 */
public interface ExtensionPoint {

    Collection<Scene> focus();

}
