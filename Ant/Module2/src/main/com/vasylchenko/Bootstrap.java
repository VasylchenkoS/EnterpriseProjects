package com.vasylchenko;

import com.vasylchenko.implementations.ExecutorImpl;
import com.vasylchenko.implementations.LongTask;

public class Bootstrap {
    public static void main(String[] args) {
        ExecutorImpl<Integer> integerExecutor = new ExecutorImpl<>();
        integerExecutor.addTask(new LongTask(1, -2));
        integerExecutor.addTask(new LongTask(1, 2), result -> result >= 0);
        integerExecutor.addTask(new LongTask(1, -2), result -> result >= 0);
        integerExecutor.addTask(new LongTask(Integer.MAX_VALUE, 1), result -> result >= 0);
        integerExecutor.execute();
        System.out.println("[ValidResult:]");
        integerExecutor.getValidResults().forEach(System.out::println);
        System.out.println("[InvalidResult:]");
        integerExecutor.getInvalidResults().forEach(System.out::println);
    }
}
