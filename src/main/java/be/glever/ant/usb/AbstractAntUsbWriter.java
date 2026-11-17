package be.glever.ant.usb;

import be.glever.ant.AntException;
import be.glever.ant.message.AntMessage;

abstract public class AbstractAntUsbWriter {

    abstract public void write(AntMessage message) throws AntException;
}
