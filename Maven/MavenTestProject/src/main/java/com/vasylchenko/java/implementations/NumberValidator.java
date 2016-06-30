package com.vasylchenko.java.implementations;

import com.vasylchenko.java.interfaces.Validator;

public class NumberValidator<T> implements Validator<T> {

    @Override
    public boolean isValid(T result) {
        return false;
    }
}
