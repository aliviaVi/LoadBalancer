package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

public class ThreadOuterServerService implements Runnable {

    private Socket socket;
    private InetAddress inetAddress;
    private int balancerPortNumber;

    public ThreadOuterServerService(Socket socket, InetAddress inetAddress, int balancerPortNumber) {
        this.socket = socket;
        this.inetAddress = inetAddress;
        this.balancerPortNumber = balancerPortNumber;
    }

    @Override
    public void run() {
        try {
            BufferedReader inputInfoFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String messageFromClient = inputInfoFromClient.readLine();
            System.out.println(messageFromClient + " - task is in process");

            PrintStream outputInfoForClient = new PrintStream(socket.getOutputStream());

            //UDP connection with Balancer - request for a free Handler
            UDPBalancerConnection udpBalancerConnection = new UDPBalancerConnection(balancerPortNumber, inetAddress);
            String handlerInfoResult = udpBalancerConnection.requestForFreeHandlerFromBalancer();

            //TCP connection with Free Handler
            TCPHandlerConnection tcpHandlerConnection = new TCPHandlerConnection(handlerInfoResult, messageFromClient);
            String updatedMessageFromClient = tcpHandlerConnection.sendTaskToFreeHandler();

            outputInfoForClient.println(updatedMessageFromClient);
            outputInfoForClient.flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
