package com.tel_ran;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperationsProvider {
    Map<String, IOperation> operationsByNameMap;
    List<String> operationPath;

    public OperationsProvider(Map<String, IOperation> operationsByNameMap) {
        this.operationsByNameMap = operationsByNameMap;

    }

    public OperationsProvider(List<String> operationPath) {
        this.operationPath = operationPath;
    }

    public IOperation getByName(String name) {
        return operationsByNameMap.get(name);
    }

    public void init() throws RuntimeException {
        operationsByNameMap = new HashMap<>();
        for (String path : operationPath) {
            try {
                IOperation iOperation = (IOperation) Class.forName(path).getConstructor().newInstance();
                operationsByNameMap.put(iOperation.getName(), iOperation);
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException | ClassNotFoundException e) {
                throw new RuntimeException();
            }
        }
    }
}
