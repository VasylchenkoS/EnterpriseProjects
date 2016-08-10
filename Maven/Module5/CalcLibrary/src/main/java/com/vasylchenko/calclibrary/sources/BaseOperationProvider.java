package com.vasylchenko.calclibrary.sources;

import com.vasylchenko.calclibrary.interfaces.Operation;
import com.vasylchenko.calclibrary.interfaces.OperationProvider;
import com.vasylchenko.calclibrary.operations.Adding;
import com.vasylchenko.calclibrary.operations.Subtraction;

import java.util.ArrayList;
import java.util.List;

public class BaseOperationProvider implements OperationProvider {

    private List<Operation> operations = new ArrayList<>();

    @Override
    public List<Operation> getAllOperations() {
        return operations;
    }

    @Override
    public void init() {
        operations.add(new Adding());
        operations.add(new Subtraction());
    }

    public void initAdditionalOperation(Operation operation){
        operations.add(operation);
    }
}
