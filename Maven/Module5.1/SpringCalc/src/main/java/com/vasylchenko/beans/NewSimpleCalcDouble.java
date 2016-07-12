package com.vasylchenko.beans;


import com.vasilchenko.interfaces.SimpleCalc;

public class NewSimpleCalcDouble implements SimpleCalc<Double> {

    public String evaluate(String s, Double a, Double b) throws IllegalArgumentException {
        OperationsDouble operations = new OperationsDouble();
        switch (s) {
            case "+":
                return operations.sumOperation(a, b);
            case "-":
                return operations.minusOperation(a, b);
            case "*":
                return operations.multiplyOperation(a, b);
            case "/":
                return operations.divisionOperation(a, b);
            case "^":
                return operations.absOperation(a, b);
            default:
                throw new IllegalArgumentException("[Error:] Unregistered operation");
        }
    }

}
