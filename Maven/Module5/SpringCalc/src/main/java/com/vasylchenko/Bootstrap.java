package com.vasylchenko;

import com.vasylchenko.calclibrary.sources.Calculator;
import com.vasylchenko.annotation.Loggable;
import com.vasylchenko.beans.Console;

public class Bootstrap {

    private Calculator calculator;

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    public Calculator getCalculator() {
        return calculator;
    }

    @Loggable
    public void run() {
        new Console().readData(calculator);
    }
}
