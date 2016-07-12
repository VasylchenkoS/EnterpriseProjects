package com.vasylchenko.beans;

import com.vasilchenko.interfaces.Operations;

public class OperationsDouble implements Operations<Double>{

    public String sumOperation(Double a, Double b) {
        return String.valueOf(a + b);
    }

    public String minusOperation(Double a, Double b) {
        return String.valueOf(a - b);
    }

    public String multiplyOperation(Double a, Double b) {
        return String.valueOf(a * b);
    }

    public String divisionOperation(Double a, Double b) {
        if (b == 0) throw new NullPointerException("[Error:] Second argument can't be 0");
        return String.valueOf(a / b);
    }

    public String absOperation(Double a, Double b) {
        return String.valueOf(Math.pow(a, b));
    }
}
