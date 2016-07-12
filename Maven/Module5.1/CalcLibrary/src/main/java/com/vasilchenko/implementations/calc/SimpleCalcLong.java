package com.vasilchenko.implementations.calc;

import com.vasilchenko.implementations.operations.OperationsLong;
import com.vasilchenko.interfaces.SimpleCalc;

public class SimpleCalcLong implements SimpleCalc<Long> {

    @Override
    public String evaluate(String operator, Long a, Long b) throws IllegalArgumentException {
        OperationsLong operations = new OperationsLong();
        switch (operator) {
            case "+":
                return operations.sumOperation(a, b);
            case "-":
                return operations.minusOperation(a, b);
            default:
                throw new IllegalArgumentException("[Error:] Unregistered operation");
        }
    }
}
