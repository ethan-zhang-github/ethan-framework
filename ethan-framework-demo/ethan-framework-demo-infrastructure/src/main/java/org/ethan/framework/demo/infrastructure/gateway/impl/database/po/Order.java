package org.ethan.framework.demo.infrastructure.gateway.impl.database.po;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@EqualsAndHashCode(of = "orderNo", callSuper = true)
@Getter
@Setter
@Document
public class Order extends AbstractDocument {

    private String orderNo;

    private Set<Product> products;

}
