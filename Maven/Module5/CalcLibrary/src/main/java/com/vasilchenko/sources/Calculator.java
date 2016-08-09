package com.vasilchenko.sources;

import com.vasilchenko.interfaces.Operation;
import com.vasilchenko.interfaces.Parser;

import java.text.ParseException;
import java.util.List;

public class Calculator {

    private Parser parser;
    private List<Operation> operationList;
    public void setParser(Parser parser) {
        this.parser = parser;
    }
    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }
    public void addOperation(Operation operation){
        operationList.add(operation);
    }

    public String calculate(String expression) throws ParseException {
        parser.expressionParser(expression);
        for (Operation operation : operationList) {
            if (parser.getOperator().equals(operation.getOperator()))
                return String.valueOf(operation.count(parser.getOperands().get(0), parser.getOperands().get(1)));
        }
        throw new IllegalArgumentException("[Error:] Unregistered operation");
    }
}
