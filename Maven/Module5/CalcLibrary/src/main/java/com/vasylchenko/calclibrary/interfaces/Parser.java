package com.vasylchenko.calclibrary.interfaces;

import java.math.BigDecimal;
import java.util.List;

public interface Parser {
    void expressionParser(String expression);
    List<BigDecimal> getOperands();
    String getOperator();
}
