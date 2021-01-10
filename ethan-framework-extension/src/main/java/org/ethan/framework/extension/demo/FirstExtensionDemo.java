package org.ethan.framework.extension.demo;

public class FirstExtensionDemo implements ExtensionDemo {

    @Override
    public void execute(Long id) {
        System.out.println("first");
    }

    @Override
    public String execute(Long id, String name) {
        return "first";
    }

}
