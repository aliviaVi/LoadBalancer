package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPBalancerConnection {

    private static final int INCOMING_DATAGRAM_SIZE = 1024;
    private int portNumber;
    private InetAddress inetAddress;

    public UDPBalancerConnection(int portNumber, InetAddress inetAddress) {
        this.portNumber = portNumber;
        this.inetAddress = inetAddress;
    }

    public String requestForFreeHandlerFromBalancer() throws IOException {

        DatagramSocket socketForBalancer = new DatagramSocket();

        byte[] outGoingData = new byte[INCOMING_DATAGRAM_SIZE];
        DatagramPacket outgoingPacket = new DatagramPacket(outGoingData, outGoingData.length, inetAddress, portNumber);
        socketForBalancer.send(outgoingPacket);

        byte[] incomingData = new byte[INCOMING_DATAGRAM_SIZE];
        DatagramPacket incomingPacket = new DatagramPacket(incomingData, INCOMING_DATAGRAM_SIZE);
        socketForBalancer.receive(incomingPacket);

        String handlerInfoResult = new String(incomingPacket.getData(), 0, incomingPacket.getLength());

        socketForBalancer.close();

        return handlerInfoResult;
    }
}
