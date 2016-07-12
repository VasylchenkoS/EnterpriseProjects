package com.vasilchenko.implementations;

import com.vasilchenko.Calculate;
import org.junit.Assert;
import org.junit.Test;

public class SimpleCalcTest {

    @Test
    public void testEvaluateInteger() throws Exception {
        Assert.assertEquals("3", new Calculate().start("+,1,2"));
        Assert.assertEquals("-1", new Calculate().start("-,1,2"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluateIntegerException() throws Exception {
        new Calculate().start("*,1,2");
    }

    @Test
    public void testEvaluateDouble() throws Exception {
        Assert.assertEquals("3.0", new Calculate().start("+,1.0,2.0"));
        Assert.assertEquals("-1.0", new Calculate().start("-,1.0,2.0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluateDoubleException() throws Exception {
        new Calculate().start("*, 1.0, 2.0");
    }


    @Test
    public void testEvaluateFloat() throws Exception {
        Assert.assertEquals("3.0", new Calculate().start("+,1.0F,2.0F"));
        Assert.assertEquals("-1.0", new Calculate().start("-,1.0F,2.0F"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testEvaluateFloatException() throws Exception {
        new Calculate().start("*, 1.0F, 2.0F");
    }

    @Test
    public void testEvaluateLong() throws Exception {
        Assert.assertEquals(String.valueOf(Long.MAX_VALUE), new Calculate().start("+," + Long.MAX_VALUE + ",0"));
        Assert.assertEquals("-1", new Calculate().start("-," + Long.MAX_VALUE + "," + Long.MIN_VALUE));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testEvaluateLongException() throws Exception {
        new Calculate().start("*," + Long.MAX_VALUE + "," + Long.MIN_VALUE);
    }
}