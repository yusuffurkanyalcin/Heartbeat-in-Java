package org.furkan;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiver{

    private final DatagramSocket socket;
    private final String HEARTBEAT_MESSAGE = "HEARTBEAT";

    public UDPReceiver(DatagramSocket socket_) {
        socket = socket_;
    }

    public void receive() {
        byte[] receiveData = createReceiveDataSize();
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            receivePacket(receivePacket);

            String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

            if (message.startsWith(HEARTBEAT_MESSAGE)) {
                System.out.println("Heartbeat received from: " + receivePacket.getAddress());
                System.out.println("Heartbeat message: " + message);
                System.out.println("##########################################");
            }
        }
    }

    private byte[] createReceiveDataSize() {
        return new byte[1024];
    }

    private void receivePacket(DatagramPacket receivePacket) {
        try {
            socket.receive(receivePacket);
        } catch (IOException e) {
            System.out.println("Receive packet failed");
        }
    }
}
