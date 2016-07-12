package com.vasilchenko.implementations.operations;

import com.vasilchenko.interfaces.Operations;

public class OperationsDouble implements Operations<Double> {
    @Override
    public String sumOperation(Double a, Double b) {
        return String.valueOf(a + b);
    }

    @Override
    public String minusOperation(Double a, Double b) {
        return String.valueOf(a - b);
    }
}
