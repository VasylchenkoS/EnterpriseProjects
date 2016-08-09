package com.vasilchenko.sources;

import com.vasilchenko.interfaces.Operation;
import com.vasilchenko.interfaces.OperationProvider;
import com.vasilchenko.operations.Adding;
import com.vasilchenko.operations.Subtraction;

import java.util.ArrayList;
import java.util.List;

public class BaseOperationProvider implements OperationProvider {

    public List<Operation> operations = new ArrayList<>();

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
