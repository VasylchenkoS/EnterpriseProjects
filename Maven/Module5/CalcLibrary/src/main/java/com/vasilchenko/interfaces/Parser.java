package com.vasilchenko.interfaces;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

public interface Parser {
    void expressionParser(String expression) throws ParseException;
    List<BigDecimal> getOperands();
    String getOperator();
}
