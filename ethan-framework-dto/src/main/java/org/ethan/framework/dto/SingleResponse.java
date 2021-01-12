package org.ethan.framework.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResponse<T> extends Response {

    private T data;

    public SingleResponse() {}

    public SingleResponse(boolean success, String errCode, String errMessage, T data) {
        super(success, errCode, errMessage);
        this.data = data;
    }

    public static <T> SingleResponse<T> of(boolean success, String errCode, String errMessage, T data) {
        return new SingleResponse<>(success, errCode, errMessage, data);
    }

    public static <T> SingleResponse<T> ofSuccess(T data) {
        return of(true, EMPTY, EMPTY, data);
    }

    public static SingleResponse<?> ofFailure(String errCode, String errMessage) {
        return of(false, errCode, errMessage, null);
    }

}
