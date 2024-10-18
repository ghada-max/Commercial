package com.Commercial.commercial.Constants;

public class EcheanceStatusDTO {
    private EcheanceStatus echeanceStatus; // Ensure the field name matches the request body

    public EcheanceStatus getEcheanceStatus() {
        return echeanceStatus;
    }

    public void setEcheanceStatus(EcheanceStatus echeanceStatus) {
        this.echeanceStatus = echeanceStatus;
    }
}

