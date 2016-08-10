package com.vasylchenko.calclibrary;

import com.vasylchenko.calclibrary.interfaces.Parser;
import com.vasylchenko.calclibrary.sources.BaseOperationProvider;
import com.vasylchenko.calclibrary.sources.Calculator;
import com.vasylchenko.calclibrary.sources.ExpressionParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

    private static Calculator calculator;

    @Before
    public void setIn(){
        BaseOperationProvider baseOperationProvider = new BaseOperationProvider();
        baseOperationProvider.init();
        Parser parser = new ExpressionParser();
        calculator = new Calculator();
        calculator.setOperationList(baseOperationProvider.getAllOperations());
        calculator.setParser(parser);
    }

    @Test
    public void testCalculate() throws Exception {
        String a = "-,-15.0,5.0";
        Assert.assertEquals("-20.0",calculator.calculate(a));
        String b = "-,15.0,-5.0";
        Assert.assertEquals("20.0",calculator.calculate(b));
    }

    @Test
    public void testCalculate1() throws Exception {
        String a = "-,15.0,-5.0";
        Assert.assertEquals("20.0",calculator.calculate(a));
    }
}