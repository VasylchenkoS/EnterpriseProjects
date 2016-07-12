package com.vasilchenko.implementations.operations;

import com.vasilchenko.interfaces.Operations;

public class OperationsLong implements Operations<Long> {

    @Override
    public String sumOperation(Long a, Long b) {
        return String.valueOf(a + b);
    }

    @Override
    public String minusOperation(Long a, Long b) {return String.valueOf(a - b);}
}
