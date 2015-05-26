package com.example.arduino.listener.util;

import gnu.io.CommPortIdentifier;

import java.util.Enumeration;

public class CommPortIdentifierFinder {

    private static final String[] DEFAULT_PORT_NAMES = {
            "/dev/tty.usbserial-A9007UX1", // Mac OS X
            "/dev/ttyACM0", // Raspberry Pi
            "/dev/ttyUSB0", // Linux
            "COM3", // Windows
    };


    private CommPortIdentifier findCommPortIdentifier() throws PortNotFoundException {
        final Enumeration commPortEnumerator = CommPortIdentifier.getPortIdentifiers();

        CommPortIdentifier commPortIdentifier = null;

        while (commPortEnumerator.hasMoreElements()) {
            final CommPortIdentifier currentCommPortIdentifier = (CommPortIdentifier) commPortEnumerator.nextElement();
            for (final String portName : DEFAULT_PORT_NAMES) {
                if (currentCommPortIdentifier.getName().equals(portName)) {
                    commPortIdentifier = currentCommPortIdentifier;
                    break;
                }
            }
        }

        if (commPortIdentifier == null) {
            throw new PortNotFoundException("Could not find COM port.");
        }

        return commPortIdentifier;
    }
}
