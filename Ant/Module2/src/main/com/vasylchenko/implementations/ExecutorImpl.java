package com.vasylchenko.implementations;

import com.vasylchenko.interfaces.Executor;
import com.vasylchenko.interfaces.Task;
import com.vasylchenko.interfaces.Validator;

import java.util.ArrayList;
import java.util.List;

public class ExecutorImpl<T> implements Executor<T> {

    private List<T> validResults = new ArrayList<>();
    private List<T> invalidResults = new ArrayList<>();
    private List<TaskAndValidator<T>> tasks = new ArrayList<>();
    private static final Validator<Object> DEFAULT_VALIDATOR = value -> true;

    private boolean executed = false;

    @Override
    public void addTask(Task<? extends T> task) {
        checkNotExecuted();
        addTask(task, DEFAULT_VALIDATOR);
    }

    private void checkNotExecuted() {
        if (executed) throw new IllegalStateException("Executor already executed");
    }

    private void checkExecuted() {
        if (!executed) throw new IllegalStateException("Executor already executed");
    }

    @Override
    public void addTask(Task<? extends T> task, Validator<? super T> validator) {
        tasks.add(new TaskAndValidator<T>(task, validator));
    }

    @Override
    public void execute() {
        checkNotExecuted();
        for (TaskAndValidator<T> taskAndValidator : tasks) {
            Task<? extends T> task = taskAndValidator.task;
            task.execute();
            if (taskAndValidator.validator.isValid(task.getResult()))
                validResults.add(task.getResult());
            else invalidResults.add(task.getResult());
        }
        executed = true;
    }

    @Override
    public List<T> getValidResults() {
        checkExecuted();
        return validResults;
    }

    @Override
    public List<T> getInvalidResults() {
        checkExecuted();
        return invalidResults;
    }

    private static class TaskAndValidator<T>{
        private Task<? extends T> task;
        private Validator<? super T> validator;

        public TaskAndValidator(Task<? extends T> task, Validator<? super T> validator) {
            this.task = task;
            this.validator = validator;
        }
    }
}

