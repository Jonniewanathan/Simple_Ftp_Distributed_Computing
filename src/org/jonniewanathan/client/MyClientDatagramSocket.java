package org.jonniewanathan.client;

import java.net.*;
import java.io.*;

/**
 * A subclass of DatagramSocket which contains
 * methods for sending and receiving messages
 *
 * @author M. L. Liu
 */
public class MyClientDatagramSocket extends DatagramSocket {
    static final int MAX_LEN = 65536;

    MyClientDatagramSocket() throws SocketException {
        super();
    }

    MyClientDatagramSocket(int portNo) throws SocketException {
        super(portNo);
    }

    public void sendMessage(InetAddress receiverHost,
                            int receiverPort,
                            String message)
                            throws IOException {
        byte[] sendBuffer = message.getBytes();
        DatagramPacket datagram =
                new DatagramPacket(sendBuffer, sendBuffer.length,
                        receiverHost, receiverPort);
        this.send(datagram);
    } // end sendMessage

    public void sendMessage(InetAddress receiverHost, int receiverPort, byte[] message)throws IOException {
        DatagramPacket datagram =
                new DatagramPacket(message, message.length,
                        receiverHost, receiverPort);
        this.send(datagram);
    } // end sendMessage

    public String receiveMessage()
            throws IOException {
        byte[] receiveBuffer = new byte[MAX_LEN];
        DatagramPacket datagram =
                new DatagramPacket(receiveBuffer, MAX_LEN);
        this.receive(datagram);
        String message = new String(receiveBuffer, 0, datagram.getLength());
        return message;
    } //end receiveMessage
} //end class
