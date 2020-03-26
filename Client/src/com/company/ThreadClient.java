package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadClient implements Runnable {

    private int mainPortNumber;
    private String address;

    public ThreadClient(int mainPortNumber, String address) {
        this.mainPortNumber = mainPortNumber;
        this.address = address;
    }

    @Override
    public void run() {

        try {
            Socket socket = new Socket(address, mainPortNumber);
            String sendingMessage = "Test task";
            System.out.println(sendingMessage);
            PrintWriter socketOutPut = new PrintWriter(socket.getOutputStream());

            BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOutPut.println(sendingMessage);
            socketOutPut.flush();
            System.out.println("Answer from the server " + socketInput.readLine());

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

