package com.vasylchenko;

import com.vasilchenko.sources.Calculator;
import com.vasylchenko.beans.Console;

public class Bootstrap {

    private Calculator calculator;

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    public void run() {
        new Console().readData(calculator);
    }
}
