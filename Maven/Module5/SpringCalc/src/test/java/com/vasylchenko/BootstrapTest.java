package com.vasylchenko;

import com.vasylchenko.configs.AppConfig;
import com.vasylchenko.configs.LoggingAspect;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.junit.*;

import java.text.ParseException;

public class BootstrapTest {

    Bootstrap bootstrap;

    @Before
    public void setUp(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class, LoggingAspect.class);
        bootstrap = applicationContext.getBean(Bootstrap.class);
    }

    @Test
    public void testRun() throws Exception {
        Assert.assertEquals("3", bootstrap.getCalculator().calculate("+,1,2"));
    }

    @Test
    public void testRun1() throws Exception {
        Assert.assertEquals("-15", bootstrap.getCalculator().calculate("*,-5,3"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testErrorOperation() throws ParseException {
        bootstrap.getCalculator().calculate("^,-5,3");
    }

    @Test(expected = NullPointerException.class)
    public void testErrorOperate() throws IllegalArgumentException, ParseException {
        bootstrap.getCalculator().calculate("/,-5,0");
    }

}