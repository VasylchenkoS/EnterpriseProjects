package com.vasilchenko.implementations.calc;

import com.vasilchenko.implementations.operations.OperationsFloat;
import com.vasilchenko.interfaces.SimpleCalc;

public class SimpleCalcFloat implements SimpleCalc<Float> {

    @Override
    public String evaluate(String operator, Float a, Float b) throws IllegalArgumentException {
        OperationsFloat operations = new OperationsFloat();
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
