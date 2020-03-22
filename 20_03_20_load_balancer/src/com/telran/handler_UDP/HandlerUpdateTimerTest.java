package com.telran.handler_UDP;

import org.junit.Test;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class HandlerUpdateTimerTest {
    HandlerUpdateTimer timer = new HandlerUpdateTimer();

    @Test
    public void test_addHandler_newHandler(){
        Map<String, LocalTime> handlersTime = new HashMap<>();
        String s1 = "3#host#3000";
        String s2 = "2#host#3010";
        String s3 = "5#host#3020";
        LocalTime t1 = LocalTime.parse("10:00:00");
        LocalTime t2 = LocalTime.parse("10:02:30");
        LocalTime t3 = LocalTime.parse("10:03:05");

        timer.addHandler(s1,t1);
        timer.addHandler(s2,t2);
        timer.addHandler(s3,t3);

        assertEquals(3, timer.getHandlersTime().size());
    }

    @Test
    public void test_addHandler_updateHandler(){
        Map<String, LocalTime> handlersTime = new HashMap<>();
        String s1 = "3#host#3000";
        String s2 = "2#host#3010";
        String s3 = "5#host#3020";
        LocalTime t1 = LocalTime.parse("10:00:00");
        LocalTime t2 = LocalTime.parse("10:02:30");
        LocalTime t3 = LocalTime.parse("10:03:05");

        timer.addHandler(s1,t1);
        timer.addHandler(s2,t2);
        timer.addHandler(s3,t3);
        timer.addHandler(s1,t2);

        assertEquals(3, timer.getHandlersTime().size());
    }

    @Test
    public void test_getKey(){
        Map<String, LocalTime> handlersTime = new HashMap<>();
        String s1 = "3#host#3000";
        String s2 = "2#host#3010";
        String s3 = "5#host#3020";
        LocalTime t1 = LocalTime.parse("10:00:00");
        LocalTime t2 = LocalTime.parse("10:02:30");
        LocalTime t3 = LocalTime.parse("10:03:05");

        timer.addHandler(s1,t1);
        timer.addHandler(s2,t2);
        timer.addHandler(s3,t3);

        String key = timer.getKey(t2);

        assertEquals(3, timer.getHandlersTime().size());
        assertEquals("2#host#3010", key);
    }

    @Test
    public void test_getKeyDelayedHandler(){
        Map<String, LocalTime> handlersTime = new HashMap<>();
        String s1 = "3#host#3000";
        LocalTime t1 = LocalTime.parse("10:00:00");

        timer.addHandler(s1,t1);


        String key = timer.getKeyDelayedHandler();

        assertEquals(1, timer.getHandlersTime().size());
        assertEquals("3#host#3000", key);
    }

}