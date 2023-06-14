package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        // TODO:在每个线程中执行的输出，在打印后发现是没有规律的，因为是操作系统来执行，不可认为操纵。

        // 启动子线程1
        new Thread1().start();
        // 启动子线程2
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