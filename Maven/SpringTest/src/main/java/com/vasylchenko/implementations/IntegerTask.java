package com.vasylchenko.implementations;

import com.vasylchenko.interfaces.Task;

public class IntegerTask implements Task<Integer> {

    private int value1;
    private int value2;
    private int result;

    public IntegerTask(int value1, int value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public void execute() {
        result = value1 + value2;
    }

    @Override
    public Integer getResult() {
        return result;
    }
}
