package com.Commercial.commercial.DAO;


import com.Commercial.commercial.Constants.MonthNumber;
import com.Commercial.commercial.Constants.PaymentMethod;
import com.Commercial.commercial.Constants.paymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Invoice")
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JsonProperty("InvoiceDetails")
    @JoinColumn(name="DevisId")
    private devis InvoiceDetails;

    @OneToMany
    private List<Echeance> echeances;


    @JsonProperty("EmissionDate")
    @Column(name="EmissionDate")
    private Date EmissionDate;


    @JsonProperty("DueDate")
    @Column(name="DueDate")
    private Date DueDate;

    @JsonProperty("ammountPaid")
    @Column(name="ammountPaid")
    private String ammountPaid;

   @Enumerated(EnumType.STRING)
   @Column(name="OnMonthNumber")
   public MonthNumber monthNumber;

   @Column(name="deliveryStatus")
   public boolean deliveryStatus;

    @Enumerated(EnumType.STRING)
    @Column(name="paymentMethod")
    public PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name="paymentStatus")
    public paymentStatus paymentStatus;
























}





