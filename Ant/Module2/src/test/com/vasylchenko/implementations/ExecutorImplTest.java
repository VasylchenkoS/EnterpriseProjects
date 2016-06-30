package com.vasylchenko.implementations;

import org.junit.*;

public class ExecutorImplTest {

    @Test
    public void testGetValidResults() throws Exception {
        ExecutorImpl<Integer> integerExecutor = new ExecutorImpl<>();
        integerExecutor.addTask(new LongTask(1, 2));
        integerExecutor.execute();
        Assert.assertEquals(integerExecutor.getValidResults().size(), 1, 0);
        Assert.assertEquals(integerExecutor.getInvalidResults().size(), 0, 0);
        Assert.assertEquals(integerExecutor.getValidResults().get(0), 3, 0);
    }

    @Test
    public void testExecuteWithValidator() throws Exception {
        ExecutorImpl<Integer> integerExecutor = new ExecutorImpl<>();
        integerExecutor.addTask(new LongTask(1, -2), result -> result >= 0);
        integerExecutor.execute();
        Assert.assertEquals(integerExecutor.getValidResults().size(), 0, 0);
        Assert.assertEquals(integerExecutor.getInvalidResults().size(), 1, 0);
        Assert.assertEquals(integerExecutor.getInvalidResults().get(0), -1, 0);
    }


    @Test
    public void testExecutor() throws Exception {
        ExecutorImpl<Integer> integerExecutor = new ExecutorImpl<>();
        integerExecutor.addTask(new LongTask(1, -2));
        integerExecutor.addTask(new LongTask(1, 2), result -> result >= 0);
        integerExecutor.addTask(new LongTask(1, -2), result -> result >= 0);
        integerExecutor.addTask(new LongTask(Integer.MAX_VALUE, 1), result -> result >= 0);
        integerExecutor.execute();
        Assert.assertEquals(integerExecutor.getValidResults().size(), 2, 0);
        Assert.assertEquals(integerExecutor.getInvalidResults().size(), 2, 0);
        Assert.assertEquals(integerExecutor.getValidResults().get(0), -1, 0);
        Assert.assertEquals(integerExecutor.getValidResults().get(1), 3, 0);
        Assert.assertEquals(integerExecutor.getInvalidResults().get(0), -1, 0);
        Assert.assertEquals(integerExecutor.getInvalidResults().get(1), Integer.MIN_VALUE, 0);
    }
}