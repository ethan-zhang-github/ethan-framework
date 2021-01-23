package org.ethan.framework.demo.infrastructure.gateway.impl.database.model;

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

    private Long orderId;

    private String name;

    private BigDecimal price;

}
