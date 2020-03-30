package com.tel_ran;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.atomic.AtomicInteger;

public class UdpSender implements Runnable {

   private AtomicInteger counterTasks;
    private String balancerHost;
    DatagramSocket socket = new DatagramSocket();
    private int balancerUdpPort;
    private int handlerTcpPort;


    public UdpSender(AtomicInteger counterTasks , String balancerHost, int balancerUdpPort, int handlerTcpPort) throws SocketException {


    }



    @Override
    public void run() {
        try {
        String integerString = counterTasks.toString();
        String res=integerString.concat("#").concat(String.valueOf(handlerTcpPort));
        byte[] dataOut = res.getBytes();

        DatagramPacket packetOut = new DatagramPacket(
                dataOut,
                dataOut.length,
                InetAddress.getByName(balancerHost),
                balancerUdpPort);

            socket.send(packetOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

