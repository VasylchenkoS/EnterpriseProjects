package com.vasylchenko.calclibrary.sources;

import com.vasylchenko.calclibrary.interfaces.Parser;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ExpressionParser implements Parser {

    private List<BigDecimal> operands;
    private String operator;

    public void expressionParser(String expression) {
        operands = new ArrayList<>();
        expression = expression.replace(" ", "");
        StringTokenizer stringTokenizer = new StringTokenizer(expression, ",");
        operator = stringTokenizer.nextToken();
        operands.add(new BigDecimal(stringTokenizer.nextToken()));
        operands.add(new BigDecimal(stringTokenizer.nextToken()));
    }

    @Override
    public List<BigDecimal> getOperands() {
        return operands;
    }

    @Override
    public String getOperator() {
        return operator;
    }
}