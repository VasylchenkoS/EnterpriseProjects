package com.vasylchenko.java.sources;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

public class SquareSumTest {

    private static int[] createArray(int num){
        int[] ints = new int[num];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = i;
        }
        return ints;
    }

    private static int checkValue(int[] current){
        final int[] result = {0};
        IntStream.range(0, current.length).forEach(i -> result[0] += current[i]*current[i]);
        return result[0];
    }

    @Test(groups = "CyclicBarrier")
    public void testGetSquareSum() throws Exception {
        int[] testArray = createArray(50);
        Assert.assertEquals(new SquareSum().getSquareSum(testArray, 2), checkValue(testArray));
    }


    @Test(groups = {"CyclicBarrier"}, expectedExceptions = IllegalArgumentException.class)
    public void testReleaseThrowsException() throws ExecutionException, InterruptedException {
        new SquareSum().getSquareSum(createArray(5), -1);
    }
}