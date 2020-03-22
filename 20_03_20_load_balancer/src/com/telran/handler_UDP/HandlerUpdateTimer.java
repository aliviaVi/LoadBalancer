package com.telran.handler_UDP;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class HandlerUpdateTimer {
    long secondsFromStart;

//    public HandlerUpdateTimer () {
//    }

    private Map<String, LocalTime> handlersTime = new HashMap<>();

    public Map<String, LocalTime> getHandlersTime() {
        return handlersTime;
    }

    public void addHandler(String receivedHandler, LocalTime time) {
        handlersTime.put(receivedHandler, time);
    }

    public String getKey(LocalTime value) {
        for (Map.Entry<String, LocalTime> entry : handlersTime.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public String getKeyDelayedHandler () {
        LocalTime now = LocalTime.now();
        for (LocalTime timeValue : handlersTime.values()) {
            secondsFromStart = Duration.between(timeValue, now).getSeconds();
            if(secondsFromStart>5){
                return getKey(timeValue);
            }
        }
        return null;
    }

}


