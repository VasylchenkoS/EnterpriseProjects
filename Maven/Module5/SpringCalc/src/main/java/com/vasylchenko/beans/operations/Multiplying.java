package com.vasylchenko.beans.operations;

import com.vasilchenko.interfaces.Operation;

import java.math.BigDecimal;

public class Multiplying implements Operation {

    @Override
    public BigDecimal count(BigDecimal value1, BigDecimal value2) {
        return value1.multiply(value2);
    }

    @Override
    public String getOperator() {
        return "*";
    }
}
