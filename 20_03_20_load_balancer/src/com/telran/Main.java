package com.telran;

import com.telran.handler_UDP.HandlerUDPReceiver;
import com.telran.handler_UDP.HandlerUpdateTimer;
import com.telran.handler_repository.HandlerRepository;
import com.telran.outer_server_UDP_Channel.OuterServerReceiver;
import com.telran.outer_server_UDP_Channel.OuterServerSender;

import java.io.IOException;
import java.net.DatagramSocket;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws IOException {
        HandlerRepository repository = new HandlerRepository();
        HandlerUpdateTimer timer = new HandlerUpdateTimer();//timer counter

        //receives new handlers
        HandlerUDPReceiver handlerReceiver = new HandlerUDPReceiver();
        String receivedMsg = handlerReceiver.handlerReceiver();
        //adds new handler into repository
        repository.addHandler(receivedMsg);

        //adds handler and its time into time counter
        timer.addHandler(receivedMsg, handlerReceiver.getTime());

        //thread, which controls handlers every 5 seconds and deletes if delay more than 5 seconds
        Thread control5Seconds = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String delayedHandler = timer.getKeyDelayedHandler();
                        if (delayedHandler != null) {
                            repository.removeHandler(delayedHandler);
                        }
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        });
        control5Seconds.start();

        //control of data
        System.out.println(timer.getHandlersTime());
        System.out.println(repository.getHandlers());
        System.out.println(repository.getHandler());

        //Connection with OuterServer
        OuterServerReceiver serverReceiver = new OuterServerReceiver();
        //gets handler from repository and sends to OuterServer
        String handlerForServer = repository.getHandler();
        serverReceiver.receivedCall(handlerForServer);

        System.out.println(repository.getHandler());


    }
}
