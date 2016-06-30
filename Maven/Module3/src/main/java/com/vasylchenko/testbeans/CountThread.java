package com.vasylchenko.testbeans;

import com.vasylchenko.java.sources.SemaphoreImpl;

public class CountThread implements Runnable {
    CommonResource res;
    SemaphoreImpl sem;
    String name;

    public CountThread(CommonResource res, SemaphoreImpl sem, String name) {
        this.res = res;
        this.sem = sem;
        this.name = name;
    }

    public void run() {
        try {
            runThreadWithNumber(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runThreadWithNumber(int N) throws InterruptedException {
        System.out.println(name + " ожидает разрешение");
        sem.acquire(N);
        res.x = 1;
        for (int i = 1; i < 5; i++) {
            System.out.println(this.name + ": " + res.x);
            res.x++;
            Thread.sleep(100);
        }
        System.out.println(name + " освобождает разрешение");
        sem.release(N);
    }

    public static class CommonResource {
        int x = 0;
    }
}

