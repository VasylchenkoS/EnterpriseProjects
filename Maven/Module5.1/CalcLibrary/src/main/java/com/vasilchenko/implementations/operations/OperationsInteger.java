package com.vasilchenko.implementations.operations;

import com.vasilchenko.interfaces.Operations;

public class OperationsInteger implements Operations<Integer> {

    @Override
    public String sumOperation(Integer a, Integer b) {
        return String.valueOf(a + b);
    }

    @Override
    public String minusOperation(Integer a, Integer b) {
        return String.valueOf(a - b);
    }
}
