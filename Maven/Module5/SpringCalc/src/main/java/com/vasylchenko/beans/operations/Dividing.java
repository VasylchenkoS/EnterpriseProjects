package com.vasylchenko.beans.operations;

import com.vasilchenko.interfaces.Operation;

import java.math.BigDecimal;


public class Dividing implements Operation {
    @Override
    public BigDecimal count(BigDecimal value1, BigDecimal value2) {
        if (value2.intValue() == 0)
            throw new NullPointerException();
        return value1.divide(value2);
    }

    @Override
    public String getOperator() {
        return "/";
    }
}
