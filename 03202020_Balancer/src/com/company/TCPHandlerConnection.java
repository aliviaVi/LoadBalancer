package com.company;

import java.io.*;
import java.net.Socket;

public class TCPHandlerConnection {

    private String handlerInfoResult;
    private String messageFromClient;

    public TCPHandlerConnection(String handlerInfoResult, String messageFromClient) {
        this.handlerInfoResult = handlerInfoResult;
        this.messageFromClient = messageFromClient;

    }

    public String sendTaskToFreeHandler() throws IOException {

        if (handlerInfoResult == null) {// if balancer doesn`t answer //TODO put correct boolean expression
        }

        String[] loadInfo = handlerInfoResult.split("#"); //""load#TCPPort#IP""
        int freeHandlerPortNumber = Integer.parseInt(loadInfo[1]);
        String freeHandlerIPAddress = loadInfo[2];

        Socket socketForHandler = new Socket(freeHandlerIPAddress, freeHandlerPortNumber);
        PrintStream outputForHandler = new PrintStream(socketForHandler.getOutputStream());
        outputForHandler.println(messageFromClient);

        BufferedReader socketInput = new BufferedReader(new InputStreamReader(socketForHandler.getInputStream())); //receive info from Handler
        String answerFromHandler = socketInput.readLine();
        System.out.println(answerFromHandler);
        socketForHandler.close();
        return answerFromHandler;
    }
}
