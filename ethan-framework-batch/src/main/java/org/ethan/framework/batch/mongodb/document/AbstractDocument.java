package org.ethan.framework.batch.mongodb.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigInteger;

@Getter
@Setter
public class AbstractDocument {

    @Id
    private BigInteger id;

}
