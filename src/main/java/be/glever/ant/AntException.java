package be.glever.ant;

import be.glever.ant.message.AntMessage;

import java.io.IOException;
import java.io.Serial;

/**
 * Ant Exception.
 */
public class AntException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * ANT message associated with this exception.
     */
    private AntMessage antMessage;

    /**
     * Constructs a new {@link AntException}.
     *
     * @param message the message
     */
    public AntException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@link AntException}.
     *
     * @param message the message
     * @param e the e
     */
    public AntException(String message, IOException e) {
        super(message, e);
    }

    /**
     * Constructs a new {@link AntException}.
     *
     * @param t the t
     */
    public AntException(Throwable t) {
        super(t);
    }

    /**
     * Constructs a new {@link AntException}.
     *
     * @param antMessage the ant message
     */
    public AntException(AntMessage antMessage) {
        this("Received error message " + antMessage.getClass().getName());
        this.antMessage = antMessage;
    }

    /**
     * Returns the ant message.
     *
     * @return the ant message.
     */
    @SuppressWarnings("unused")
    public AntMessage getAntMessage() {
        return antMessage;
    }
}
