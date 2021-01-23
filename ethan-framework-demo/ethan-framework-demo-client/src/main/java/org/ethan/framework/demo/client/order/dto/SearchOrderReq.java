package org.ethan.framework.demo.client.order.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SearchOrderReq {

    private Date startTime;

    private Date endTime;

}
