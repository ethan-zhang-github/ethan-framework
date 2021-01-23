package org.ethan.framework.demo.infrastructure.gateway.impl.database.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@EqualsAndHashCode(of = "orderNo", callSuper = true)
@Getter
@Setter
@Document
public class Order extends AbstractDocument {

    private String orderNo;

    private String buyerName;

    private String buyerPhone;

    private Date createTime;

}
