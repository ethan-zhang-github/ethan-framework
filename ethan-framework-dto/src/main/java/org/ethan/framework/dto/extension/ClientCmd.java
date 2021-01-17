package org.ethan.framework.dto.extension;

import lombok.Getter;
import lombok.Setter;
import org.ethan.framework.dto.Command;

@Getter
@Setter
public class ClientCmd extends Command {

    private Client client;

}
