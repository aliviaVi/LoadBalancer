package com.telran.handler_repository;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class HandlerRepository {
    static final String DELIMITER = "#";

    private PriorityQueue<String> handlers = new PriorityQueue<>();

    public HandlerRepository() {
    }

    public PriorityQueue<String> getHandlers() {
        return handlers;
    }

    public void addHandler(String receivedMsg) {
//        if (handlers.size() == 0) {
//            handlers.add(receivedMsg);
//        } else
            if (!handlers.contains(receivedMsg)) {
            handlers.removeIf(handler -> handler.substring(handler.indexOf(DELIMITER) + 1)
                    .equals(receivedMsg.substring(receivedMsg.indexOf(DELIMITER) + 1)));
            handlers.add(receivedMsg);
        }
    }

    public String getHandler() {
        return handlers.peek(); //returns handler with min loads.
    }

    public void removeHandler(String handler) {
        handlers.remove(handler);

        //TODO remove if handler didn't send call more than 5 sec

    }

}
