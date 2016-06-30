package com.vasylchenko.java.sources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class SquareSum {

    public long getSquareSum(int[] values, int numberOfThreads) throws InterruptedException, ExecutionException {
        if (numberOfThreads <= 0) throw new IllegalArgumentException("[Error:] Number of Threads must be more than 0");
        Phaser phaser = new Phaser(numberOfThreads);

        long resultSquareSum = 0;
        int separator = values.length / numberOfThreads;
        System.out.println(Thread.currentThread().getName() + ". Current Phaser arrived parties: " + phaser.getArrivedParties());

        List<Callable<Long>> callable = new ArrayList<>();

        IntStream.range(0, numberOfThreads).forEach(i -> {
            int[] current = new int[separator];
            int a = i * separator;
            for (int j = 0; j < separator; j++, a++)
                current[j] = values[a];
            System.out.println(Arrays.toString(current));
            phaser.register();
            callable.add(() -> {
                phaser.arrive();
                System.out.println(Thread.currentThread().getName() + ". Current Phaser arrived parties: " + phaser.getArrivedParties());
                Thread.sleep(1000);
                return sum(current);
            });
        });
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Long>> result = executorService.invokeAll(callable);
        for (Future intFuture : result) {
            resultSquareSum += (long) intFuture.get();
        }
        executorService.shutdown();
        System.out.println("\nResult: " + resultSquareSum);
        return resultSquareSum;
    }

    long sum(int[] value) {
        final int[] result = {0};
        IntStream.range(0, value.length).forEach(i -> result[0] += value[i] * value[i]);
        return result[0];
    }
}
