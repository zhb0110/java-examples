package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(4); // 固定4个线程池
        Future<BigDecimal> future = es.submit(new Task("601857")); // 加了1个线程，注意返回的是Future类型
        System.out.println(future.get()); // 获得返回值
        es.shutdown(); // 关闭线程池
    }
}

class Task implements Callable<BigDecimal> { // TODO:实现的是Callable接口，内部是泛型，很方便

    public Task(String code) {
    }

    @Override
    public BigDecimal call() throws Exception {
        Thread.sleep(1000);
        double d = 5 + Math.random() * 20; // 随机数
        return new BigDecimal(d).setScale(2, RoundingMode.DOWN); // setScale 四舍五入
    }
}