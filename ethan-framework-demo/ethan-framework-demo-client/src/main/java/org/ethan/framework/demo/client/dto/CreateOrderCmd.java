package org.ethan.framework.demo.client.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateOrderCmd extends ClientCmd {

    private Long productId;

    private Integer productNum;

}
