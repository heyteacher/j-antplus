package be.glever.ant.channel;

import be.glever.ant.constants.AntNetworkKeys;

/**
 * Ant Channel Network.
 */
public class AntChannelNetwork {
    private byte networkNumber;
    private byte[] networkKey;

    /**
     * Constructs a new {@link AntChannelNetwork}.
     *
     * @param networkNumber the network number
     * @param key the key
     */
    public AntChannelNetwork(byte networkNumber, AntNetworkKeys key) {
        this.networkNumber = networkNumber;
        this.networkKey = key.getBytes();
    }

    /**
     * Returns the network number.
     *
     * @return the network number.
     */
    public byte getNetworkNumber() {
        return networkNumber;
    }

    /**
     * Sets the network number.
     *
     * @param networkNumber the network number
     */
    @SuppressWarnings("unused")
    public void setNetworkNumber(byte networkNumber) {
        this.networkNumber = networkNumber;
    }

    /**
     * Returns the network key.
     *
     * @return the network key.
     */
    public byte[] getNetworkKey() {
        return networkKey;
    }

    /**
     * Sets the network key.
     *
     * @param networkKey the network key
     */
    @SuppressWarnings("unused")
    public void setNetworkKey(byte[] networkKey) {
        this.networkKey = networkKey;
    }

}
