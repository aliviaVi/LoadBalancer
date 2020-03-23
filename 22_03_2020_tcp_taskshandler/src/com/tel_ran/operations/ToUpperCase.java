package com.tel_ran.operations;

import com.tel_ran.IOperation;

public class ToUpperCase implements IOperation {

    private static final String NAME="uppercase";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String operate(String input) {

        return input.toUpperCase();
    }
}
