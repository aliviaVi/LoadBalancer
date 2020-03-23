package com.company;

import java.io.IOException;

public class OuterServer {

    static final int MAIN_OUTER_SERVER_PORT = 2000;
    static final int BALANCER_PORT = 3000;

    public static void main(String[] args) throws IOException {

        //TCP connection with Client - receiving the tasks
        TCPClientConnection tcpClientConnection = new TCPClientConnection(MAIN_OUTER_SERVER_PORT, BALANCER_PORT);
        tcpClientConnection.receiveTaskFromClient();

    }
}

