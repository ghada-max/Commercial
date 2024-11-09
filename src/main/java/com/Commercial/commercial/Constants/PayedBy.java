package com.Commercial.commercial.Constants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PayedBy {

        BANK_TRANSFER("bank transfer"),
        CREDIT_CARD("credit card"),
        CASH("cash");

    private final String label;

    public String getLabel(){
        return label;
    }

}
