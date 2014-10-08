package general;

import java.io.InputStream;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.TooManyListenersException;

public class czytnik implements SerialPortEventListener {

    SerialPort serialPort;
    /**
     * The port we're normally going to use.
     */
    private static final String PORT_NAMES[] = {"COM8"};
    /**
     * Buffered input stream from the port
     */
    private InputStream input;
    /**
     * The output stream to the port
     */
    private OutputStream output;
    /**
     * Milliseconds to block while waiting for port open
     */
    private static final int TIME_OUT = 2000;
    /**
     * Default bits per second for COM port.
     */
    private static final int DATA_RATE = 9600;

    private String print = "";
    private long startPause;
    private int licznik = 0;

    public void initialize() {
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        // iterate through, looking for the port
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            }
        }

        if (portId == null) {
            System.out.println("Could not find COM port.");
            return;
        }

        try {
            // open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);

            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            // open the streams
            input = serialPort.getInputStream();
            output = serialPort.getOutputStream();

            // add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (PortInUseException | UnsupportedCommOperationException | IOException | TooManyListenersException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * This should be called when you stop using the port. This will prevent
     * port locking on platforms like Linux.
     */
    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    /**
     * Handle an event on the serial port. Read the data and print it.
     *
     * @param oEvent
     */
    @Override
    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if ((startPause + 500) <= System.currentTimeMillis()) {
            if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                try {
                    int available = input.available();
                    byte chunk[] = new byte[available];
                    input.read(chunk, 0, available);

                    // Displayed results are codepage dependent
                    print = print += new String(chunk).replaceAll("\\s", "");
                    //System.out.print(new String(chunk).replaceAll("\\s",""));
                } catch (Exception e) {
                    System.err.println(e.toString());
                }
                if (print.length() >= 26) {
                    licznik++;
                    startPause = System.currentTimeMillis();
                    System.out.println(print);
                    SaveToDatabase.sendToDataDB(print);
                    print = "";

                }
            }
        }
        // Ignore all the other eventTypes, but you should consider the other ones.
    }

    public static void main(String[] args) throws Exception {
        try {
                        Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
                        while (networks.hasMoreElements()) {
                            NetworkInterface network = networks.nextElement();
                            byte[] mac = network.getHardwareAddress();

                            if (mac != null) {
                                System.out.print("Current MAC address : ");

                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < mac.length; i++) {
                                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                                }
                                System.out.println(sb.toString());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
        czytnik main = new czytnik();
        main.initialize();
        System.out.println("Started");
    }
}
