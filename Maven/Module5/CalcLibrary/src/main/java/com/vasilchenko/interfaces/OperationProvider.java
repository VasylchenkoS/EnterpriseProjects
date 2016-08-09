package com.vasilchenko.interfaces;

import java.util.List;

public interface OperationProvider {

    List<Operation> getAllOperations();
    void init();
    void initAdditionalOperation(Operation operation);
}
