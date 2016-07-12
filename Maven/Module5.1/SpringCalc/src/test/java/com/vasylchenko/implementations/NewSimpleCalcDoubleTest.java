package com.vasylchenko.implementations;


import com.vasilchenko.Calculate;
import com.vasylchenko.beans.NewSimpleCalcDouble;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewSimpleCalcDoubleTest {

    @Test
    public void testEvaluateDouble() throws Exception {
        Assert.assertEquals("3.0", new Calculate(new NewSimpleCalcDouble()).start("+,1.0,2.0"));
        Assert.assertEquals("-1.0", new Calculate(new NewSimpleCalcDouble()).start("-,1.0,2.0"));
        Assert.assertEquals("2.0", new Calculate(new NewSimpleCalcDouble()).start("*,1.0,2.0"));
        Assert.assertEquals("0.5", new Calculate(new NewSimpleCalcDouble()).start("/,1.0,2.0"));
        Assert.assertEquals("1.0", new Calculate(new NewSimpleCalcDouble()).start("^,1.0,2.0"));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEvaluateDoubleException() throws Exception {
        new Calculate(new NewSimpleCalcDouble()).start("asda, 1.0, 2.0");
    }

}