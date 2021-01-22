package org.ethan.framework.demo.domain.order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class CreateOrderMessage {

    private Long id;

    private String orderNo;

    private Set<Product> products;

    @Data
    public static class Product {

        private String name;

        private BigDecimal price;

    }

}
