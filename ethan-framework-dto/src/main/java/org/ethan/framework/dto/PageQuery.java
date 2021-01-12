package org.ethan.framework.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PageQuery extends Query {

    private int page = 1;

    private int pageSize = 10;

    private String orderBy;

    private boolean asc;

    private String groupBy;

}
