package com.example.arduino;

import com.example.arduino.listener.SerialPortListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class.getCanonicalName());
    private static final String DEFAULT_PORT_NAME = "/dev/ttyACM0";

    public static void main(String[] args) {
        LOGGER.info("App started with args: {}", args);

        final SerialPortListener serialPortListener = new SerialPortListener(providePortName(args));
        serialPortListener.initialize();
    }

    private static String providePortName(String[] args) {
        return args.length == 1 ? args[0] : DEFAULT_PORT_NAME;
    }
}
