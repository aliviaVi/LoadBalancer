package com.tel_ran.operations;

import com.tel_ran.IOperation;

public class ReverseOperation implements IOperation {
    private static final String NAME="reverse";
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String operate(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}
