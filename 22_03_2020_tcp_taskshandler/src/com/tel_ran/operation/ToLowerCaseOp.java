package com.tel_ran.operation;

import com.tel_ran.IOperation;

public class ToLowerCaseOp implements IOperation {
    private static final String NAME ="lowercase";


    @Override
    public String operate(String input) {
        return input.toLowerCase();
    }

    @Override
    public String getName(){
        return NAME;
    }
}
