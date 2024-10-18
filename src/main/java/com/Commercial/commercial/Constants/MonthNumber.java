package com.Commercial.commercial.Constants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MonthNumber {
    THREE_MONTH("On 3 Month"),
    SIX_MONTH("On 6 Month");

    private final String label;

     public String getLabel(){
         return label;
     }

}
