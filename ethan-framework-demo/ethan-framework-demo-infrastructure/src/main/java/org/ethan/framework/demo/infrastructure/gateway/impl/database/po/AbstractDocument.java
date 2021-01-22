package org.ethan.framework.demo.infrastructure.gateway.impl.database.po;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class AbstractDocument {

    @Id
    private Long id;

}
