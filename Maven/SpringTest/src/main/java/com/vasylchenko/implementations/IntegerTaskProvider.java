package com.vasylchenko.implementations;

import com.vasylchenko.interfaces.Task;
import com.vasylchenko.interfaces.TaskProvider;

import java.util.ArrayList;
import java.util.List;

public class IntegerTaskProvider implements TaskProvider<Integer> {

    private List<Task<Integer>> tasks = new ArrayList<>();

    @Override
    public List<Task<Integer>> getAllTasks() {
        return tasks;
    }

    public void init(){
        tasks.add(new IntegerTask(1, -2));
        tasks.add(new IntegerTask(1, 2));
        tasks.add(new IntegerTask(1, -2));
        tasks.add(new IntegerTask(Integer.MAX_VALUE, 1));
    }
}
