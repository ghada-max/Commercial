package com.Commercial.commercial.Constants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum paymentStatus {
    PENDING("Pending"),
    PAYED("Payed"),
    PARTIALLY_PAYED("Partially payed"),
    DELAY("Delay,Non Payed");

    private final String label; // A private field to store the label

    // Getter method to retrieve the label
    public String getLabel() {
        return label;
    }

}
