package be.glever.ant.channel;

import be.glever.ant.constants.AntPlusDeviceType;

/**
 * AntChannelId.
 */
public class AntChannelId {

    private AntChannelTransmissionType transmissionType;
    private byte deviceType; // set by master device, 0 for slave devices. TODO MSB is a pairing bit. split device type from pairing bit?
    private byte[] deviceNumber; // TODO remember this is little endian

    /**
     * Create a new AntChannelId.
     * @param transmissionType transmission type
     * @param deviceType device type
     * @param deviceNumber device number
     */
    public AntChannelId(AntChannelTransmissionType transmissionType, AntPlusDeviceType deviceType, byte[] deviceNumber) {
        this.transmissionType = transmissionType;
        this.deviceType = deviceType.value();
        this.deviceNumber = deviceNumber;
    }

    /**
     * Returns the transmission type.
     *
     * @return the transmission type.
     */
    public AntChannelTransmissionType getTransmissionType() {
        return transmissionType;
    }

    /**
     * Sets the transmission type.
     *
     * @param transmissionType the transmission type
     */
    @SuppressWarnings("unused")
    public void setTransmissionType(AntChannelTransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    /**
     * Returns the device type.
     *
     * @return the device type.
     */
    public byte getDeviceType() {
        return deviceType;
    }

    /**
     * Sets the device type.
     *
     * @param deviceType the device type
     */
    @SuppressWarnings("unused")
    public void setDeviceType(byte deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * Returns the device number.
     *
     * @return the device number.
     */
    public byte[] getDeviceNumber() {
        return deviceNumber;
    }

    /**
     * Sets the device number.
     *
     * @param deviceNumber the device number
     */
    @SuppressWarnings("unused")
    public void setDeviceNumber(byte[] deviceNumber) {
        this.deviceNumber = deviceNumber;
    }


}
