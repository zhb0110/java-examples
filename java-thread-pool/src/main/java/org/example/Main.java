package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 6; i++) { // TODO:超过4个了，由于是固定的，所以不会增加新线程，有两个线程会等待
            es.submit(new Task("" + i));
        }
        es.shutdown();
    }
}

class Task implements Runnable {

    private final String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("start task " + name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("end task " + name);
    }
}