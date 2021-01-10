package org.ethan.framework.extension.demo;

public class SecondExtensionDemo implements ExtensionDemo {

    @Override
    public void execute(Long id) {
        System.out.println("second");
    }

    @Override
    public String execute(Long id, String name) {
        return "second";
    }

}
