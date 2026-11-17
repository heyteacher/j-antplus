package be.glever.ant.usb;

import be.glever.ant.message.AntBlockingMessage;
import be.glever.ant.message.AntMessage;
import be.glever.ant.message.control.RequestMessage;

/**
 * Request Matcher.
 */
public class RequestMatcher {

    /**
     * Constructs a new {@link RequestMatcher}.
     */
    public RequestMatcher() {
    }

    /**
     * Checks if matching response.
     *
     * @param message the message
     * @param response the response
     * @return {@code true} if matching response, {@code false} otherwise.
     */
    public static boolean isMatchingResponse(AntBlockingMessage message, AntMessage response) {
        if (message instanceof RequestMessage) {
            return response.getMessageId() == ((RequestMessage) message).getMsgIdRequested();
        }

        return response.getMessageId() == message.getResponseMessageId();
    }

    /**
     * Checks if matching response.
     *
     * @param request the request
     * @param response the response
     * @return {@code true} if matching response, {@code false} otherwise.
     */
    public static boolean isMatchingResponse(AntMessage request, AntMessage response) {
        if (request instanceof AntBlockingMessage) {
            return isMatchingResponse((AntBlockingMessage) request, response);
        }

        return false;
    }

}
