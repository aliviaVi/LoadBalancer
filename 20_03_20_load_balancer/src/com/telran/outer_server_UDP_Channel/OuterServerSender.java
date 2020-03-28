package com.telran.outer_server_UDP_Channel;

import com.telran.handler_repository.HandlerRepository;
import org.junit.experimental.theories.Theories;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class OuterServerSender implements Runnable {
    static final int INCOMING_DATAGRAM_SIZE = 1024;
    private DatagramSocket socket;
    private DatagramPacket incomingPacket;
    private String handler;

    public OuterServerSender(DatagramSocket socket, DatagramPacket incomingPacket, String handler) {
        this.socket = socket;
        this.incomingPacket = incomingPacket;
        this.handler = handler;
    }

    public void run() {
        try {
////TODO: write String with handlers information
//            String handlerForServer = handler;

            DatagramPacket outgoingPacket = new DatagramPacket(
                    handler.getBytes(),
                    handler.length(),
                    incomingPacket.getAddress(),
                    incomingPacket.getPort());

            socket.send(outgoingPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
