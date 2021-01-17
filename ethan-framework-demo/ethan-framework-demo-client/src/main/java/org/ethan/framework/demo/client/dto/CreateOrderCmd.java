package org.ethan.framework.demo.client.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ethan.framework.dto.extension.ClientCmd;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateOrderCmd extends ClientCmd {

    private Long productId;

    private Integer productNum;

}
