package org.ethan.framework.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateOrderCmd extends ClientCmd {

    private Long productId;

    private Integer productNum;

}
