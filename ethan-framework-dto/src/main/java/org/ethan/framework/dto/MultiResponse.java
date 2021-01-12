package org.ethan.framework.dto;

import java.util.*;
import java.util.stream.Stream;

public class MultiResponse<T> extends SingleResponse<Collection<T>> {

    public MultiResponse() {}

    public MultiResponse(boolean success, String errCode, String errMessage, Collection<T> data) {
        super(success, errCode, errMessage, data);
    }

    public static <T> MultiResponse<T> of(boolean success, String errCode, String errMessage, Collection<T> data) {
        return new MultiResponse<>(success, errCode, errMessage, data);
    }

    public static <T> MultiResponse<T> ofSuccess(Collection<T> data) {
        return of(true, EMPTY, EMPTY, data);
    }

    public static MultiResponse<?> ofFailure(String errCode, String errMessage) {
        return of(false, errCode, errMessage, null);
    }

    public Stream<T> stream() {
        return getData().stream();
    }

    public List<T> asList() {
        return new ArrayList<>(getData());
    }

    public Set<T> asSet() {
        return new HashSet<>(getData());
    }

}
