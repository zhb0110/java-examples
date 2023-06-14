package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        // TODO:每次3个任务一起执行，执行完成后再执行下一波任务
        // TODO:每个线程中的人物都可以自由拿到本线程的同一变量，而且不会被其他的影响

        ExecutorService es = Executors.newFixedThreadPool(3); // 固定3个线程的线程池
        String[] users = new String[]{"Bob", "Alice", "Tim", "Mike", "Lily", "Jack", "Bush"};
        for (String user : users) {
            es.submit(new Task(user)); // 建了7个任务提交给线程池
        }
        es.awaitTermination(3, TimeUnit.SECONDS); // 等待终止线程池，等3秒
        es.shutdown(); // TODO:推荐使用，和上面的语句一样，关掉线程池，这个在内部线程执行完成后就会关闭线程池
    }
}

class UserContext implements AutoCloseable {
    private static final ThreadLocal<String> userThreadLocal = new ThreadLocal<>();

    public UserContext(String name) {
        userThreadLocal.set(name);
        System.out.printf("初始化局部线程变量 [%s] init user %s...\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
    }

    public static String getCurrentUser() {
        return userThreadLocal.get();
    }

    @Override
    public void close() {
        System.out.printf("关闭局部线程变量 [%s] cleanup for user %s...\n", Thread.currentThread().getName(),
                UserContext.getCurrentUser());
        userThreadLocal.remove(); // 关闭
    }
}

class Task implements Runnable {

    final String username;

    public Task(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        try (var ctx = new UserContext(this.username)) { // 尝试获得上下文
            // TODO:可任意调用UserContext.currentUser():

            new Task1().process();
            new Task2().process();
            new Task3().process();
        } // TODO:在此自动调用UserContext.close()方法释放ThreadLocal关联对象
    }
}

class Task1 {
    public void process() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        System.out.printf("检查任务 [%s] check user %s...\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
    }
}

class Task2 {
    public void process() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        System.out.printf("注册任务 [%s] %s registered ok.\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
    }
}

class Task3 {
    public void process() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        System.out.printf("工作任务 [%s] work of %s has done.\n", Thread.currentThread().getName(),
                UserContext.getCurrentUser());
    }
}