package com.Commercial.commercial.Constants;

public class PaymentStatusDTO {
    private paymentStatus paymentStatus; // Ensure the field name matches the request body


    public paymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(paymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
