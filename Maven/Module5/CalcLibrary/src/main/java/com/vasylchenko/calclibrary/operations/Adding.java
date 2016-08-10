package com.vasylchenko.calclibrary.operations;

import com.vasylchenko.calclibrary.interfaces.Operation;

import java.math.BigDecimal;

public class Adding implements Operation {

    @Override
    public BigDecimal count(BigDecimal value1, BigDecimal value2) {
        return value1.add(value2);
    }

    @Override
    public String getOperator() {
        return "+";
    }
}
