package com.vasilchenko.implementations.operations;

import com.vasilchenko.interfaces.Operations;

public class OperationsFloat implements Operations<Float> {
    @Override
    public String sumOperation(Float a, Float b) {
        return String.valueOf(a + b);
    }

    @Override
    public String minusOperation(Float a, Float b) {
        return String.valueOf(a - b);
    }
}
