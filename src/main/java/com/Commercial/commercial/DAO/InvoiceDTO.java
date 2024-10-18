package com.Commercial.commercial.DAO;

import com.Commercial.commercial.Constants.MonthNumber;
import com.Commercial.commercial.Constants.PaymentMethod;
import com.Commercial.commercial.Constants.paymentStatus;
import jakarta.persistence.Entity;
import lombok.Data;
import java.util.Date;

@Data
public class InvoiceDTO {

    private Integer id;
    private devisDTO invoiceDetails;
    private Date emissionDate;
    private Date dueDate;
    private Float tax;
    private PaymentMethod paymentMethod;
    private paymentStatus paymentStatus;
    private String ammountPaid;
    public MonthNumber monthNumber;


    // Constructors, Getters, and Setters
}


