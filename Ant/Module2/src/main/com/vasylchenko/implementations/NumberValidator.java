package com.vasylchenko.implementations;

import com.vasylchenko.interfaces.Validator;

public class NumberValidator<T> implements Validator<T> {

    @Override
    public boolean isValid(T result) {
        return false;
    }
}
