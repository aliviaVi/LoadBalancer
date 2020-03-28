package com.telran.handler_repository;

import org.junit.Test;

import java.util.PriorityQueue;

import static org.junit.Assert.*;

public class HandlerRepositoryTest {

    HandlerRepository hr = new HandlerRepository();

    @Test
    public void test_addHandlers_allDifference() {
        PriorityQueue<String> handlers = new PriorityQueue<>();
        String s1 = "3#host#3000";
        String s2 = "2#host#3010";
        String s3 = "5#host#3020";
        String s5 = "1#host#3030";
        hr.addHandler(s1);
        hr.addHandler(s2);
        hr.addHandler(s3);
        hr.addHandler(s5);

        assertEquals(4, hr.getHandlers().size());
    }

    @Test
    public void test_addNewHandler_size1() {
        PriorityQueue<String> handlers = new PriorityQueue<>();
        String s1 = "3#host#3000";

        hr.addHandler(s1);

        assertEquals(1, hr.getHandlers().size());
    }

    @Test
    public void test_addHandlers_RepeatedHandlerDifferentLoads() {
        PriorityQueue<String> handlers = new PriorityQueue<>();
        String s1 = "3#host#3000";
        String s2 = "2#host#3010";
        String s3 = "5#host#3020";

        hr.addHandler(s1);
        hr.addHandler(s2);
        hr.addHandler(s3);

        assertEquals(3, hr.getHandlers().size());

        String s4 = "5#host#3000";
        hr.addHandler(s4);

        assertEquals(3, hr.getHandlers().size());
    }

    @Test
    public void test_addHandlers_RepeatedHandlerNewMinLoad() {
        PriorityQueue<String> handlers = new PriorityQueue<>();
        String s1 = "3#host#3000";
        String s2 = "2#host#3010";
        String s3 = "5#host#3020";
        String s5 = "1#host#3030";
        hr.addHandler(s1);
        hr.addHandler(s2);
        hr.addHandler(s3);
        hr.addHandler(s5);

        assertEquals(4, hr.getHandlers().size());

        String s6 = "1#host#3020";
        hr.addHandler(s6);

        assertEquals(4, hr.getHandlers().size());
    }

    @Test
    public void test_addHandlers_RepeatedHandlerTheSameLoad() {
        PriorityQueue<String> handlers = new PriorityQueue<>();
        String s1 = "3#host#3000";
        String s2 = "2#host#3010";
        String s3 = "5#host#3020";
        String s5 = "1#host#3030";
        hr.addHandler(s1);
        hr.addHandler(s2);
        hr.addHandler(s3);
        hr.addHandler(s5);

        assertEquals(4, hr.getHandlers().size());

        String s6 = "5#host#3020";
        hr.addHandler(s6);

        assertEquals(4, hr.getHandlers().size());
    }

    @Test
    public void test_getHandlers_size4() {
        PriorityQueue<String> handlers = new PriorityQueue<>();
        String s1 = "3#host#3000";
        String s2 = "2#host#3010";
        String s3 = "5#host#3020";
        String s5 = "1#host#3030";
        hr.addHandler(s1);
        hr.addHandler(s2);
        hr.addHandler(s3);
        hr.addHandler(s5);

        assertEquals("1#host#3030", hr.getHandler());
        assertEquals(4, hr.getHandlers().size());
    }

    @Test
    public void test_removeHandler_size3() {
        PriorityQueue<String> handlers = new PriorityQueue<>();
        String s1 = "3#host#3000";
        String s2 = "2#host#3010";
        String s3 = "5#host#3020";
        String s5 = "1#host#3030";
        hr.addHandler(s1);
        hr.addHandler(s2);
        hr.addHandler(s3);
        hr.addHandler(s5);

        assertEquals(4, hr.getHandlers().size());

        hr.removeHandler("5#host#3020");
        assertEquals(3, hr.getHandlers().size());
    }
}