package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        new Thread1().start();
        new Thread2().start();
        for (int i = 0; i < 100; i++) {
            System.out.println("main: running...");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }
    }
}

class Thread1 extends Thread {

    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Thread-1: running...");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }
    }
}

class Thread2 extends Thread {

    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Thread-2: running...");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }
    }
}