package be.glever.ant.message;

/**
 * Marker interface to indicate that ANT will generate a response.
 */
public interface AntBlockingMessage extends AntMessage {

    /**
     * Returns the response message id.
     *
     * @return The message id of the response message.
     */
    byte getResponseMessageId();
}
