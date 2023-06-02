package org.example;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("args = " + Arrays.deepToString(args));

//        ExecutorService es = Executors.newVirtualThreadPerTaskExecutor();
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 100000; i++) {
//            es.submit(() -> {
//                Thread.sleep(1000);
//                return 0;
//            });
//        }
//        es.close();
//        long end = System.currentTimeMillis();
//        System.out.printf("All virtual threads end at %s ms.\n", end - start);
    }
}