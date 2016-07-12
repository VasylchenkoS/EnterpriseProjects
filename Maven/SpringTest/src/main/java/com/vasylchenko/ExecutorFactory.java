package com.vasylchenko;

import com.vasylchenko.interfaces.Executor;

public abstract class ExecutorFactory {

    public abstract Executor<Integer> getIntegerExecutor();
}
