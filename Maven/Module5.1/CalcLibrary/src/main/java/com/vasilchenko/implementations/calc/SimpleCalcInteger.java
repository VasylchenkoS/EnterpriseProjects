package com.vasilchenko.implementations.calc;

import com.vasilchenko.implementations.operations.OperationsInteger;
import com.vasilchenko.interfaces.SimpleCalc;

public class SimpleCalcInteger implements SimpleCalc<Integer> {

    @Override
    public String evaluate(String operator, Integer a, Integer b) throws IllegalArgumentException{
        OperationsInteger operations = new OperationsInteger();
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
