package com.Commercial.commercial.Constants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PaymentMethod{
    PAYE_SINGLE_INSTALLMENT("payment on receipt"),
    PAYE_MULTIPLE_INSTALLMENTS("payment schedule");
 //   PENDING("Pending");




    private final String label; // A private field to store the label

    // Getter method to retrieve the label
    public String getLabel() {
        return label;
    }
}
