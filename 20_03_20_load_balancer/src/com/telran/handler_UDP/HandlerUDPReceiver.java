package com.telran.handler_UDP;

import com.telran.handler_repository.HandlerRepository;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.time.LocalTime;
import java.util.Date;

public class HandlerUDPReceiver {
    static final String DELIMITER = "#";
    static final int INCOMING_DATAGRAM_SIZE = 1024;
    static final int PORT = 3010;
    private String receivedMsg;
    private LocalTime time;

    public String getReceivedMsg() {
        return receivedMsg;
    }

    public LocalTime getTime() {
        return time;
    }

    public String handlerReceiver () throws IOException {

        DatagramSocket socket = new DatagramSocket(PORT);

        while(true) {
            byte[] incomingData = new byte[INCOMING_DATAGRAM_SIZE];
            DatagramPacket incomingPacket = new DatagramPacket(incomingData, INCOMING_DATAGRAM_SIZE);

            socket.receive(incomingPacket);

            time = LocalTime.now();//time fixing
            return receivedMsg = new String(incomingPacket.getData(), 0, incomingPacket.getLength())
                    + DELIMITER
                    + incomingPacket.getAddress();
        }

        //TODO: 1 second for every handler (probably in HandlerRepository/method "isReceived:boolean" with timer)

    }

}
