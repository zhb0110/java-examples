package org.example;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Main {
    public static void main(String[] args) throws Exception {
        // 创建2000个随机数组成的数组:
        long[] array = new long[2000];
        long expectedSum = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = random();
            expectedSum += array[i];
        }
        System.out.println("Expected sum: " + expectedSum);
        // fork/join:
        ForkJoinTask<Long> task = new SumTask(array, 0, array.length);
        long startTime = System.currentTimeMillis(); // 当前时间
        Long result = ForkJoinPool.commonPool().invoke(task); // invoke 调用
        long endTime = System.currentTimeMillis(); // 结束时间
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }

    static Random random = new Random(0);

    static long random() {
        return random.nextInt(10000);
    }
}

class SumTask extends RecursiveTask<Long> {
    static final int THRESHOLD = 500; // 限制单个任务的数量
    long[] array;
    int start;
    int end;

    SumTask(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    // TODO:注意这里是覆盖写的
    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            // 如果任务足够小,直接计算:
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += this.array[i];
                // 故意放慢计算速度:
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
            }
            return sum;
        }
        // 任务太大,一分为二:
        int middle = (end + start) / 2;
        System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
        SumTask subtask1 = new SumTask(this.array, start, middle); // 先拆分为 0~1000   内部拆分 0~500, 500~1000 TODO:内部无需再次拆分，所以只汇总计算和 第1次
        SumTask subtask2 = new SumTask(this.array, middle, end); // 1000~2000 内部拆分 1000~1500, 1500~2000 TODO:内部无需再次拆分，所以只汇总计算和 第2次
        invokeAll(subtask1, subtask2); // invokeAll(调用所有) 会并行运行两个子任务:
        Long subresult1 = subtask1.join();
        Long subresult2 = subtask2.join();
        Long result = subresult1 + subresult2; // TODO:汇总计算和 第3次
        System.out.println("result = " + subresult1 + " + " + subresult2 + " ==> " + result); // TODO:由于前面的三次计算和，所以，只会有3次输出
        return result;
    }
}