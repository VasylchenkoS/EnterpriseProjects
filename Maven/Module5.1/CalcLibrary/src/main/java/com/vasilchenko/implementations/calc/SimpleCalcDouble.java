package com.vasilchenko.implementations.calc;

import com.vasilchenko.implementations.operations.OperationsDouble;
import com.vasilchenko.interfaces.SimpleCalc;

public class SimpleCalcDouble implements SimpleCalc<Double> {

    @Override
    public String evaluate(String operator, Double a, Double b) throws IllegalArgumentException {
        OperationsDouble operations = new OperationsDouble();
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
