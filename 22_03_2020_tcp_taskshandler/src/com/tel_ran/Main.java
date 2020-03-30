package com.tel_ran;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

/*
* first args in main config - tcp port
*
*
* text format from outerServer: text#operation(toLowerCase, toUpperCase, Reverse)*/

    private static final String BALANCER_HOST = "localhost";
    private static final int UDP_BALANCER_PORT = 3700;
    public static AtomicInteger counterTasks=new AtomicInteger(0);

    public static void main(String[] args) throws IOException {




        int tcpHandlerPort=Integer.parseInt(args[0]);
        ServerSocket server = new ServerSocket(tcpHandlerPort);
        PropertiesService propertiesService=new PropertiesService("config/config.props");


        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<String> operationPath = propertiesService.getOperationPaths();
        OperationsProvider op = new OperationsProvider(operationPath);
        op.init();

        TcpReceiverHandler receiverService;

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        UdpSender udpSender = new UdpSender(counterTasks, BALANCER_HOST,UDP_BALANCER_PORT,tcpHandlerPort);

        ScheduledFuture scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(
                udpSender,
                2,
                1,
                TimeUnit.SECONDS);

        scheduledFuture.cancel(false);

        while (true) {

            Socket socketIn = server.accept();
            counterTasks.getAndIncrement();
            receiverService = new TcpReceiverHandler(op,socketIn, counterTasks);

            executorService.execute(receiverService);

        }


    }
}
