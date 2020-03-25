package com.company;

public class Client {

    static final int PORT = 2000;
    static final String HOST = "localhost";

    public static void main(String[] args) {
        int counter = 10;
        while (counter != 0) {
            ThreadClient threadClient = new ThreadClient(PORT, HOST);
            Thread thread = new Thread(threadClient);
            thread.start();
            counter--;
        }
    }
}
