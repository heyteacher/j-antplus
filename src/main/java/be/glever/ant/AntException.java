package be.glever.ant;

import be.glever.ant.message.AntMessage;

import java.io.IOException;
import java.io.Serial;

public class AntException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private AntMessage antMessage;

    public AntException(String message) {
        super(message);
    }

    public AntException(String message, IOException e) {
        super(message, e);
    }

    public AntException(Throwable t) {
        super(t);
    }

    public AntException(AntMessage antMessage) {
        this("Received error message " + antMessage.getClass().getName());
        this.antMessage = antMessage;
    }

    @SuppressWarnings("unused")
    public AntMessage getAntMessage() {
        return antMessage;
    }
}
