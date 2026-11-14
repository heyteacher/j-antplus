package be.glever.ant.message;

import be.glever.ant.AntException;
import be.glever.ant.message.channel.ChannelEventOrResponseMessage;
import be.glever.ant.message.configuration.*;
import be.glever.ant.message.control.*;
import be.glever.ant.message.data.BroadcastDataMessage;
import be.glever.ant.message.notification.SerialErrorMessage;
import be.glever.ant.message.notification.StartupNotificationMessage;
import be.glever.ant.message.requestedresponse.AntVersionMessage;
import be.glever.ant.message.requestedresponse.CapabilitiesResponseMessage;
import be.glever.ant.message.requestedresponse.ChannelStatusMessage;
import be.glever.ant.message.requestedresponse.SerialNumberMessage;
import be.glever.ant.util.ByteUtils;
import be.glever.util.logging.Log;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.lang.String.format;

/**
 * Container and factory for all known ant messages.
 */
public class AntMessageRegistry {
    private static final Log LOG = Log.getLogger(AntMessageRegistry.class);
    private static final Map<Byte, Function<byte[], AbstractAntMessage>> registry = new HashMap<>();

    static {
        // configuration
        registry.put(AssignChannelMessage.MSG_ID, AssignChannelMessage::new);
        registry.put(ChannelPeriodMessage.MSG_ID, ChannelPeriodMessage::new);
        registry.put(ChannelRfFrequencyMessage.MSG_ID, ChannelRfFrequencyMessage::new);
        registry.put(SearchTimeoutMessage.MSG_ID, SearchTimeoutMessage::new);
        registry.put(UnassignChannelMessage.MSG_ID,UnassignChannelMessage::new);
        registry.put(NetworkKeyMessage.MSG_ID,NetworkKeyMessage::new);
        registry.put(ChannelIdMessage.MSG_ID,ChannelIdMessage::new);
        // control
        registry.put(CloseChannelMessage.MSG_ID,CloseChannelMessage::new);
        registry.put(OpenChannelMessage.MSG_ID,OpenChannelMessage::new);
        registry.put(OpenRxScanModeMessage.MSG_ID,OpenRxScanModeMessage::new);
        registry.put(RequestMessage.MSG_ID,RequestMessage::new);
        registry.put(ResetSystemMessage.MSG_ID,ResetSystemMessage::new);
        // notification
        registry.put(StartupNotificationMessage.MSG_ID,StartupNotificationMessage::new);
        registry.put(SerialErrorMessage.MSG_ID,SerialErrorMessage::new);
        registry.put(BroadcastDataMessage.MSG_ID,BroadcastDataMessage::new);
        // requested response
        registry.put(AntVersionMessage.MSG_ID,AntVersionMessage::new);
        registry.put(ChannelStatusMessage.MSG_ID,ChannelStatusMessage::new);
        registry.put(SerialNumberMessage.MSG_ID,SerialNumberMessage::new);
        registry.put(CapabilitiesResponseMessage.MSG_ID,CapabilitiesResponseMessage::new);
        // Channel events
        registry.put(ChannelEventOrResponseMessage.MSG_ID,ChannelEventOrResponseMessage::new);
    }

    /**
     * Parses the given bytes into the correct {@link AbstractAntMessage}
     * implementation. Precondition: the bytes represent 1 valid Ant Message (from
     * SYNC to checksum).
     *
     * @param bytes the bytes to parse
     * @return the parsed {@link AbstractAntMessage}
     */
    public static AntMessage from(final byte[] bytes) throws AntException {
        byte msgLength = (byte) (bytes[1] + 4); // sync, msgLength, msgId and checksum excluded
        byte msgId = bytes[2];
        final byte[] msgBytes = Arrays.copyOf(bytes, msgLength);
        if (registry.containsKey(msgId)) {
            final AntMessage antMessage = registry.get(msgId).apply(msgBytes);
            LOG.debug(() -> format("Converted %s to %s", ByteUtils.hexString(msgBytes), antMessage.getClass().getSimpleName()));
            return antMessage;
        }
        else {
            LOG.warn(() -> format("Message not converted, return UnknowMessage to %s", ByteUtils.hexString(msgBytes)));
            return new UnknownMessage(msgBytes);
        }
    }
}
