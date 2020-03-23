package com.tel_ran;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.atomic.AtomicInteger;

public class UdpSender implements Runnable {

    AtomicInteger integer;

    public UdpSender(AtomicInteger integer) throws SocketException, UnknownHostException {
        this.integer = integer;
    }


    private static final int SERVER_PORT = 3001;
    private static final String SERVER_HOST = "localhost";
    public boolean isAlive = true;


    DatagramSocket socket = new DatagramSocket();


    @Override
    public void run() {
        while (isAlive) {
            String integerString = integer.toString();
            String res=integerString.concat("#").concat(String.valueOf(SERVER_PORT));
            byte[] dataOut = res.getBytes();

            DatagramPacket packetOut = new DatagramPacket(
                    dataOut,
                    dataOut.length);
            try {
                socket.send(packetOut);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }
}

