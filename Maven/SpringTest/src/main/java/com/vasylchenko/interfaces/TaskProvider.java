package com.vasylchenko.interfaces;

import java.util.List;

public interface TaskProvider<T> {
    List<Task<T>> getAllTasks();
}
