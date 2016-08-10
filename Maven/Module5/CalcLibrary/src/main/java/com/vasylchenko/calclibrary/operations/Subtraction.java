package com.vasylchenko.calclibrary.operations;


import com.vasylchenko.calclibrary.interfaces.Operation;

import java.math.BigDecimal;

public class Subtraction implements Operation {
    @Override
    public BigDecimal count(BigDecimal value1, BigDecimal value2) {
        return value1.subtract(value2);
    }

    @Override
    public String getOperator() {
        return "-";
    }
}
