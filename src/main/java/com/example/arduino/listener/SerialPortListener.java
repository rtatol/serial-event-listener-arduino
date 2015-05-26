package com.example.arduino.listener;

import gnu.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TooManyListenersException;

public class SerialPortListener implements SerialPortEventListener, Closeable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerialPortListener.class.getCanonicalName());
    //
    private static final int DEFAULT_TIME_OUT = 2000;
    private static final int DEFAULT_DATA_RATE = 9600;
    //
    private SerialPort serialPort;
    // input stream from port
    private BufferedReader input;
    // bits per second for port
    private final int portDataRate;
    // milliseconds to block while waiting for port open
    private final int portTimeOut;
    // port name
    private final String portName;

    public SerialPortListener(final String portName) {
        this.portDataRate = DEFAULT_DATA_RATE;
        this.portTimeOut = DEFAULT_TIME_OUT;
        this.portName = portName;
    }

    public SerialPortListener(final int portDataRate, final int portTimeOut, final String portName) {
        this.portDataRate = portDataRate;
        this.portTimeOut = portTimeOut;
        this.portName = portName;
    }

    public void initialize() {
        LOGGER.debug("initialize");
        System.setProperty("gnu.io.rxtx.SerialPorts", portName);

        try {
            final CommPortIdentifier commPortIdentifier = CommPortIdentifier.getPortIdentifier(portName);//findCommPortIdentifier();

            // init serial port
            serialPort = (SerialPort) commPortIdentifier.open(this.getClass().getName(), portTimeOut);
            serialPort.setSerialPortParams(portDataRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            // init input
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));

            // add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);

        } catch (PortInUseException | UnsupportedCommOperationException | TooManyListenersException e) {
            LOGGER.error("RXTX error", e);
        } catch (IOException e) {
            LOGGER.error("IO error", e);
        } catch (NoSuchPortException e) {
            LOGGER.error("Port error", e);
        }
    }

    // handle an event on the serial port
    @Override
    public synchronized void serialEvent(final SerialPortEvent serialPortEvent) {
        if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                final String inputLine = input.readLine();
                LOGGER.info(inputLine);
            } catch (Exception e) {
                LOGGER.error("Event reading error", e);
            }
        }
    }

    // prevent port locking on platforms like Linux.
    @Override
    public synchronized void close() throws IOException {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }

        if (input != null) {
            input.close();
        }
    }

}
