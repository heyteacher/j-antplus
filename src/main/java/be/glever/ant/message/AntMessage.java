package be.glever.ant.message;

import be.glever.ant.AntException;

/**
 * ANT message: Ant.
 */
public interface AntMessage {

    /**
     * Returns the message id.
     *
     * @return the message id.
     */
    byte getMessageId();

    /**
     * parse the message from the given byte array
     * @param bytes the byte array
     * @throws AntException if an error occurs
     */
    void parse(byte[] bytes) throws AntException;

    /**
     * convert the message to byte array
     * @return the byte array of the message
     */
    byte[] toByteArray();

}
