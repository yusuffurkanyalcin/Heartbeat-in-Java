package org.furkan;


import java.io.IOException;
import java.net.*;

public class UDPSender implements Runnable{

    private final DatagramSocket socket;
    private final InetAddress IPAddress;

    private final String HEARTBEAT_MESSAGE = "HEARTBEAT";
    private final int PORT = 9876;

    public UDPSender(DatagramSocket socket, InetAddress IPAddress) {
        this.socket = socket;
        this.IPAddress = IPAddress;
    }

    @Override
    public void run() {
        send();
    }

    public void send() {
        byte[] sendData;
        for (int i=0; i<3; i++) {
            String modifiedData = HEARTBEAT_MESSAGE + "-" + i;
            sendData = modifiedData.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, PORT);
            sendPacket(sendPacket);

            System.out.println("Heartbeat sent to server");
            System.out.println("##########################################");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void sendPacket(DatagramPacket packet) {
        try {
            socket.send(packet);
        } catch (IOException e) {
            System.out.println("Send packet failed");
        }
    }

}
