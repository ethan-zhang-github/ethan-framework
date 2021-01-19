package org.ethan.framework.batch.demo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {

    private Long id;

    private String orderNo;

    private BigDecimal amount;

}
