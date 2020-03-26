package com.company;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPClientConnection {

    private int outerServerPortNumber;
    private int balancerPortNumber;

    public TCPClientConnection(int outerServerPortNumber, int balancerPortNumber) {
        this.outerServerPortNumber = outerServerPortNumber;
        this.balancerPortNumber = balancerPortNumber;
    }

    public void receiveTaskFromClient() throws IOException {
        ServerSocket mainSocket = new ServerSocket(outerServerPortNumber);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        InetAddress serverIp = InetAddress.getByName("localhost");

        while (true) {
            Socket socketForClient = mainSocket.accept();
            ThreadOuterServerService threadOuterServer = new ThreadOuterServerService(socketForClient, serverIp, balancerPortNumber);
            executor.execute(threadOuterServer);
        }
    }
}
