package com.vasilchenko.interfaces;



public interface SimpleCalc<T> {

    String evaluate(String operator, T a, T b) throws IllegalArgumentException;

}
