package com.vasylchenko.java.sources;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SemaphoreImplTest {

    private SemaphoreImpl semaphore;

    @BeforeMethod
    public void setUp() {
        semaphore = new SemaphoreImpl(10);
    }

    @Test(groups = {"semaphore"})
    public void testGetAvailablePermitsForSingleThread() throws Exception {
        semaphore.acquire();
        Assert.assertEquals(semaphore.getAvailablePermits(), 9, "[Error:]Number of permits no match");
        semaphore.acquire();
        Assert.assertEquals(semaphore.getAvailablePermits(), 8, "[Error:]Number of permits no match");
        semaphore.acquire();
        Assert.assertEquals(semaphore.getAvailablePermits(), 7, "[Error:]Number of permits no match");
        semaphore.release();
        semaphore.release();
        semaphore.release();
        semaphore.release();
        Assert.assertEquals(semaphore.getAvailablePermits(), 10, "[Error:]Number of permits no match");
    }

    @Test(groups = {"semaphore"})
    public void testGetAvailablePermitsForMultiThread() throws Exception {
        semaphore.acquire(3);
        Assert.assertEquals(semaphore.getAvailablePermits(), 7, "[Error:]Number of permits no match");
        semaphore.acquire(2);
        Assert.assertEquals(semaphore.getAvailablePermits(), 5, "[Error:]Number of permits no match");
        semaphore.release(4);
        Assert.assertEquals(semaphore.getAvailablePermits(), 9, "[Error:]Number of permits no match");
        semaphore.acquire(5);
        Assert.assertEquals(semaphore.getAvailablePermits(), 4, "[Error:]Number of permits no match");
        semaphore.release(11);
        Assert.assertEquals(semaphore.getAvailablePermits(), 10, "[Error:]Number of permits no match");
    }

    @Test(groups = {"semaphore"}, expectedExceptions = IllegalArgumentException.class)
    public void testAcquireThrowsException() {
        semaphore.acquire(-1);
    }

    @Test(groups = {"semaphore"}, expectedExceptions = IllegalArgumentException.class)
    public void testReleaseThrowsException() {
        semaphore.release(-1);
    }
}