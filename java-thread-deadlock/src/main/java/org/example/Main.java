package org.example;

public class Main {

    static final Object LOCK_A = new Object();
    static final Object LOCK_B = new Object();

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
    }

    static void sleep1s() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread1 extends Thread {

    public void run() {
        System.out.println("Thread-1: try get lock A...");
        synchronized (Main.LOCK_A) { // 尝试持有锁
            System.out.println("Thread-1: lock A got.");
            Main.sleep1s();
            System.out.println("Thread-1: try get lock B...");
            synchronized (Main.LOCK_B) { // 尝试持有锁
                System.out.println("Thread-1: lock B got.");
                Main.sleep1s();
            }
            System.out.println("Thread-1: lock B released."); // 释放lock B
        }
        System.out.println("Thread-1: lock A released."); // 释放lock A
    }
}

class Thread2 extends Thread {

    // TODO:死锁的写法
//    public void run() {
//        System.out.println("Thread-2: try get lock B...");
//        synchronized (Main.LOCK_B) { // 尝试持有锁
//            System.out.println("Thread-2: lock B got.");
//            Main.sleep1s();
//            System.out.println("Thread-2: try get lock A...");
//            synchronized (Main.LOCK_A) { // 尝试持有锁
//                System.out.println("Thread-2: lock A got.");
//                Main.sleep1s();
//            }
//            System.out.println("Thread-2: lock A released.");
//        }
//        System.out.println("Thread-2: lock B released.");
//    }

    // 多线程获取锁一致
    public void run() {
        System.out.println("Thread-2: try get lock A...");
        synchronized (Main.LOCK_A) { // TODO:尝试持有锁，如果抢不过去就等待，等那边lock A 释放了才会继续执行
            System.out.println("Thread-2: lock A got.");
            Main.sleep1s();
            System.out.println("Thread-2: try get lock B...");
            synchronized (Main.LOCK_B) { // 尝试持有锁
                System.out.println("Thread-2: lock B got.");
                Main.sleep1s();
            }
            System.out.println("Thread-2: lock B released.");
        }
        System.out.println("Thread-2: lock A released.");
    }
}