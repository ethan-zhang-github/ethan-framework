package org.ethan.framework.demo.client.order.dto;

import lombok.Data;

@Data
public class SearchOrderResp {
    
    private Long id;
    
    private String orderNo;
    
    private String buyerName;
    
    private String buyerPhone;
    
}
