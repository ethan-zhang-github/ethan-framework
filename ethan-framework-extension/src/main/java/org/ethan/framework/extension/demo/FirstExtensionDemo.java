package org.ethan.framework.extension.demo;

import org.ethan.framework.extension.Scene;

import java.util.Collection;

public class FirstExtensionDemo implements ExtensionDemo {

    @Override
    public void execute(Long id) {
        System.out.println("first");
    }

    @Override
    public String execute(Long id, String name) {
        return "first";
    }

    @Override
    public Collection<Scene> focus() {
        return null;
    }

}
