package com.Commercial.commercial.Constants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum devisStatus {

    PENDING("pending"),
    ACCEPTED("Accepted"),
    REFUSED("Refused");


    private final String label; // A private field to store the label

    // Getter method to retrieve the label
    public String getLabel() {
        return label;
    }

}
