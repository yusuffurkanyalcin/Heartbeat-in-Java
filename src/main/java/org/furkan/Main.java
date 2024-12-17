package org.furkan;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Main {
    public static void main(String[] args) {

        DatagramSocket socket = createSocket();
        InetAddress IPAddress = createInetAddress();


        UDPSender sender = new UDPSender(socket, IPAddress);
        UDPReceiver receiver = new UDPReceiver(socket);

        Thread senderThread = new Thread(sender);
        senderThread.start();

        receiver.receive();

    }

    public static DatagramSocket createSocket() {
        try {
            return new DatagramSocket(9876);
        } catch (SocketException exception) {
            System.out.println("Create socket failed");
        }
        return null;
    }

    public static InetAddress createInetAddress() {
        try {
            return InetAddress.getByName("localhost");
        } catch (IOException exception) {
            System.out.println("Create socket failed");
        }
        return null;
    }
}