package com.tel_ran;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    static final int PORT=2001; // check outerServer Port ARINA
    private static final int UDP_PORT = 3001; // check udpSendingPort Lena
    private static final String SERVER_HOST = "localhost";
    public static AtomicInteger counterTasks=new AtomicInteger(0);

    public static void main(String[] args) throws IOException {




        ServerSocket server = new ServerSocket(PORT);
        PropertiesService propertiesService=new PropertiesService("config/config.props");


        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<String> operationPath = propertiesService.getOperationPaths();
        OperationsProvider op = new OperationsProvider(operationPath);
        op.init();

        TcpReceiverHandler receiverService;


        while (true) {
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
            UdpSender udpSender = new UdpSender(counterTasks);

            ScheduledFuture scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(
                    udpSender,
                    2,
                    1,
                    TimeUnit.SECONDS);

            scheduledFuture.cancel(false); //if it's correct?

            Socket socket = server.accept();
            counterTasks.getAndIncrement();


            receiverService = new TcpReceiverHandler(op,socket);

            executorService.execute(receiverService);
            counterTasks.decrementAndGet();



        }


    }
}
