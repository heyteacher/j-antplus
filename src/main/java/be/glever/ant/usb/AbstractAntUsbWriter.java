package be.glever.ant.usb;

import be.glever.ant.AntException;
import be.glever.ant.message.AntMessage;

/**
 * Abstract base class for Abstract Ant Usb Writer.
 */
abstract public class AbstractAntUsbWriter {

    /**
     * Constructs a new {@link AbstractAntUsbWriter}.
     */
    protected AbstractAntUsbWriter() {
    }

    /**
     * Write.
     *
     * @param message the message
     * @throws AntException if an error occurs
     */
    abstract public void write(AntMessage message) throws AntException;
}
