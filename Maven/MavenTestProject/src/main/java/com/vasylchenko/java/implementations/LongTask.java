package com.vasylchenko.java.implementations;

import com.vasylchenko.java.interfaces.Task;

public class LongTask implements Task<Integer> {

    private int value1;
    private int value2;
    private int result;

    public LongTask(int value1, int value2) {
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
