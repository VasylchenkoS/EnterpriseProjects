package com.vasylchenko.gui.util;

import org.junit.Assert;
import org.junit.Test;


public class DateUtilTest {

    @Test
    public void testParseString() throws Exception {
        String input = "1954-05-23";
        Assert.assertEquals("1954-05-23", DateUtil.parseString(input).toString());
    }
}