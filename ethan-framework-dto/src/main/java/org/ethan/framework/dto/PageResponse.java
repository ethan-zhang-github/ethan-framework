package org.ethan.framework.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class PageResponse<T> extends MultiResponse<T> {

    private int page;

    private int pageSize;

    private int total;

    public PageResponse(boolean success, String errCode, String errMessage, Collection<T> data, int page, int pageSize, int total) {
        super(success, errCode, errMessage, data);
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
    }

    public static <T> PageResponse<T> of(boolean success, String errCode, String errMessage, Collection<T> data, int page, int pageSize, int total) {
        return new PageResponse<>(success, errCode, errMessage, data, page, pageSize, total);
    }

}
