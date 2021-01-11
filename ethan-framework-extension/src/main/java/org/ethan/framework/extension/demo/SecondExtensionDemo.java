package org.ethan.framework.extension.demo;

import org.ethan.framework.extension.Scene;

import java.util.Collection;

public class SecondExtensionDemo implements ExtensionDemo {

    @Override
    public void execute(Long id) {
        System.out.println("second");
    }

    @Override
    public String execute(Long id, String name) {
        return "second";
    }

    @Override
    public Collection<Scene> focus() {
        return null;
    }

}
