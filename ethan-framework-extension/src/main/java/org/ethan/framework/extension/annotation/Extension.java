package org.ethan.framework.extension.annotation;

import org.ethan.framework.dto.extension.Client;
import org.ethan.framework.extension.Scene;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 标注某业务扩展点
 * @author Ethan Zhang
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Extension {

    /**
     * 一级业务标识，平台，例如 wanshifu
     */
    String[] platform() default Scene.DEFAULT_PLATFORM;

    /**
     * 二级业务标识，模块，例如 order
     */
    String[] module() default Scene.DEFAULT_MODULE;

    /**
     * 三级业务标识，功能点，例如 createOrder
     */
    String[] function() default Scene.DEFAULT_FUNCTION;

    /**
     * 客户端类型，例如 WEB
     */
    Client[] client() default Client.DEFAULT;

    /**
     * 扩展点注册类型
     */
    Class<?>[] target() default {};

}
