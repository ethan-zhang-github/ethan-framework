package org.ethan.framework.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response extends DTO {

    protected static final String EMPTY = "";

    private static final Response SUCCESS = new Response(true, EMPTY, EMPTY);

    private boolean success;

    private String errCode;

    private String errMessage;

    public Response() {}

    public Response(boolean success, String errCode, String errMessage) {
        this.success = success;
        this.errCode = errCode;
        this.errMessage = errMessage;
    }

    public static Response of(boolean success, String errCode, String errMessage) {
        return new Response(success, errCode, errMessage);
    }

    public static Response ofSuccess() {
        return SUCCESS;
    }

    public static Response ofFailure(String errCode, String errMessage) {
        return of(false, errCode, errMessage);
    }

}
