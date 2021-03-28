package org.ethan.framework.microservices.api.gateway.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {

    private static final String SUCCESS_CODE = "success";
    private static final String SUCCESS_MESSAGE = "成功";

    private T data;

    private String code;

    private String message;

    public Response() {}

    public Response(T data, String code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public static <R> Response<R> success(String message) {
        return success(null, message);
    }

    public static <R> Response<R> success(R data) {
        return success(data, SUCCESS_MESSAGE);
    }

    public static <R> Response<R> success(R data, String message) {
        return new Response<>(data, SUCCESS_CODE, message);
    }

    public static <R> Response<R> failure(String code, String message) {
        return new Response<>(null, code, message);
    }

    public static <R> Response<R> unauthorized() {
        return new Response<>(null, "unauthorized", "未授权请求");
    }

    public static <R> Response<R> authenticateSuccessful() {
        return Response.success("用户登录授权成功");
    }

    public static <R> Response<R> authenticateFailed() {
        return new Response<>(null, "authenticate-failed", "用户身份认证失败");
    }

}
