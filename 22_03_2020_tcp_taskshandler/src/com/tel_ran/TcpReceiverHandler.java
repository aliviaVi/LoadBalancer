package com.tel_ran;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;



public class TcpReceiverHandler implements Runnable {
   // public static AtomicInteger countTasks=new AtomicInteger(0);

    private static final String WRONG_OPERATION = "wrong operation";
    private static final String INCORRECT_LINE = "incorrect line";
    private String delimiter = "#";

    private volatile static boolean isAlive = true;
    private OperationsProvider operationsProvider;

    private Socket socket;

    public TcpReceiverHandler(OperationsProvider operationsProvider, Socket socket) {
        this.operationsProvider = operationsProvider;
        this.socket = socket;
    }

    public TcpReceiverHandler() {
    }



    @Override
    public void run() {
        try {
            BufferedReader socketInput = new BufferedReader(new InputStreamReader
                    (socket.getInputStream()));
            PrintStream socketOutput=new PrintStream(socket.getOutputStream());

            String line;
            while (isAlive) {
              //  countTasks.getAndIncrement();
                if ((line = socketInput.readLine()) == null) {
                    isAlive = false;
                    return;
                }
                String[] parts = line.split(delimiter);

                if (parts.length != 2) {
                    socketOutput.println(line + delimiter + INCORRECT_LINE);
                } else {
                    String text = parts[0];
                    String operationName = parts[1];
                    IOperation operation = operationsProvider.getByName(operationName);
                    if (operation == null) {
                        socketOutput.println(line + " " + WRONG_OPERATION);
                    } else {
                        socketOutput.println( operation.operate(text));

                    }
                }
               // countTasks.decrementAndGet();
                socket.close();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

