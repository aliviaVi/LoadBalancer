package com.tel_ran.operations;

import com.tel_ran.IOperation;

public class ToLowerCase  implements IOperation {
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
