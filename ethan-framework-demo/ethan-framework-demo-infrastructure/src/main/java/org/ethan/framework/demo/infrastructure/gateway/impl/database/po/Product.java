package org.ethan.framework.demo.infrastructure.gateway.impl.database.po;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@EqualsAndHashCode(of = "name", callSuper = true)
@Getter
@Setter
@Document
public class Product extends AbstractDocument {

    @DBRef
    private Order order;

    private String name;

    private BigDecimal price;

}
