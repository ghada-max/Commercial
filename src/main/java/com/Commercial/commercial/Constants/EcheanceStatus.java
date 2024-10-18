package com.Commercial.commercial.Constants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EcheanceStatus {
    PAYED("PAYED"),
    NON_PAYED("NON PAYED"),
    PENDING("PENDING");

    private final String label;
    public String getLabel(){
        return label;
    }
}
