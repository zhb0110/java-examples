package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // TODO:测试执行守护线程：
        //  当其他非守护线程结束时，它也结束了；
        //  但是由于两个都是线程，所以两个线程最后的输出究竟哪个最后也不一定

        Thread t = new Thread1();
        // 如果注释下一行，观察Thread1的执行情况:
        t.setDaemon(true);
        t.start();
        System.out.println("main: wait 3 sec...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("main: end.");
    }
}

class Thread1 extends Thread {

    public void run() {
        for (;;) { //无线循环
            System.out.println("Thread-1: running...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }
}