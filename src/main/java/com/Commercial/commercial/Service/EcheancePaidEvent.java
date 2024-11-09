package com.Commercial.commercial.Service;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
public class EcheancePaidEvent extends ApplicationEvent {
    private final BigDecimal amount;

    public EcheancePaidEvent(Object source, BigDecimal amount) {
        super(source);
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}