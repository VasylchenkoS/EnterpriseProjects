package com.vasylchenko.java.sources;

import com.vasylchenko.java.interfaces.Semaphore;

//При помощи методов wait(), notify(), notifyAll() реализовать семафор:
public class SemaphoreImpl implements Semaphore {

    private volatile int counter;
    private static int MAX_FLOW_COUNT;
    private static final Object lock = new Object();

    public SemaphoreImpl() {
        this.counter = 0;
    }

    public SemaphoreImpl(int permits) {
        if (permits < 0) throw new IllegalArgumentException(permits + " < 0");
        this.counter = permits;
        MAX_FLOW_COUNT = permits;
    }

    // Запрашивает разрешение. Если есть свободное захватывает его.
    // Если нет - приостанавливает поток до тех пор пока не появится свободное разрешение.
    @Override
    public void acquire() {
        synchronized (lock) {
            if (counter > 0)
                counter--;
            else {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Запрашивает переданое количество разрешений.
    // Если есть переданое количество свободных разрешений захватывает их.

    // Если нет - приостанавливает поток до тех пор
    // пока не появится переданое колтчество свободных разрешений.
    @Override
    public void acquire(int permits) {
        synchronized (lock) {
            if (permits < 0) throw new IllegalArgumentException();
            if (counter - permits >= 0) {
                counter -= permits;
            } else {
                if (counter > 0)
                    lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Отпускает разрешение возвращая его семафору.
    @Override
    public void release() {
        synchronized (lock) {
            if (counter < MAX_FLOW_COUNT) {
                counter++;
                lock.notify();
            }
        }
    }

    // Отпускает переданое количество разрешений возварщая их семафору.
    @Override
    public void release(int permits) {
        synchronized (lock) {
            if (permits < 0) throw new IllegalArgumentException();
            if (counter + permits <= MAX_FLOW_COUNT) counter += permits;
            else counter = MAX_FLOW_COUNT;
            lock.notify();
        }
    }

    // Возвращает количество свободных разрешений на данный момент.
    @Override
    public int getAvailablePermits() {
        return counter;
    }
}
