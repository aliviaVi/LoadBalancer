package com.telran.outer_server_UDP_Channel;

import com.telran.handler_repository.HandlerRepository;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OuterServerReceiver {
    static final int INCOMING_DATAGRAM_SIZE = 1024;
    static final int PORT = 3000;

    public void receivedCall(String handler) throws IOException {
        DatagramSocket socket = new DatagramSocket(PORT);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        byte[] incomingData = new byte[INCOMING_DATAGRAM_SIZE];
        DatagramPacket incomingPacket = new DatagramPacket(incomingData, INCOMING_DATAGRAM_SIZE);//входящие пакеты
        socket.receive(incomingPacket);

        String receivedMsg = new String(incomingPacket.getData(), 0, incomingPacket.getLength());
        System.out.println(receivedMsg);

        OuterServerSender sp = new OuterServerSender(socket, incomingPacket, handler);
        executor.execute(sp);

    }

}
