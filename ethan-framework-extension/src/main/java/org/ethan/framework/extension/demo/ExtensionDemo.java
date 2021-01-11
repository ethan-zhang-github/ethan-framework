package org.ethan.framework.extension.demo;

import org.ethan.framework.extension.ExtensionPoint;

public interface ExtensionDemo extends ExtensionPoint {

    void execute(Long id);

    String execute(Long id, String name);

}
