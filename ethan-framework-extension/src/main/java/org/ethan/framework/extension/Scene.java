package org.ethan.framework.extension;

import lombok.*;
import org.ethan.framework.dto.extension.Client;

/**
 * 业务场景标识
 * @author Ethan Zhang
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"platform", "module", "function", "client"})
public class Scene {

    public static final String DEFAULT_PLATFORM = "defaultPlatform";
    public static final String DEFAULT_MODULE = "defaultModule";
    public static final String DEFAULT_FUNCTION = "defaultFunction";

    /**
     * 某一平台，例如 taobao
     */
    private String platform;

    /**
     * 某一模块，例如 order
     */
    private String module;

    /**
     * 某一功能，例如 createOrder
     */
    private String function;

    /**
     * 某一客户端，例如 web
     */
    private Client client;

    private static final Scene DEFAULT = new Scene();

    public static Scene of(String platform, String module, String function, Client client) {
        return new Scene(platform, module, function, client);
    }

    public static Scene of(String module, String function, Client client) {
        return new Scene(DEFAULT_PLATFORM, module, function, client);
    }

    public static Scene of(String function, Client client) {
        return new Scene(DEFAULT_PLATFORM, DEFAULT_MODULE, function, client);
    }

    public static Scene of(Client client) {
        return new Scene(DEFAULT_PLATFORM, DEFAULT_MODULE, DEFAULT_FUNCTION, client);
    }

    public static Scene ofDefault() {
        return DEFAULT;
    }

    public String getId() {
        return platform + "." + module + "." + function + "." + client;
    }

    @Override
    public String toString() {
        return getId();
    }

}
