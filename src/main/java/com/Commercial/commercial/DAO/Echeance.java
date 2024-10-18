package com.Commercial.commercial.DAO;
import com.Commercial.commercial.Constants.EcheanceStatus;

import com.Commercial.commercial.Constants.paymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Echeance")
public class Echeance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @JsonProperty("EmissionDate")
    @Column(name="EmissionDate")
    private Date EmissionDate;




    @JsonProperty("DueDate")
    @Column(name="DueDate")
    private Date DueDate;


    @JsonProperty("Amount")
    @Column(name="Amount")
    private Float Amount;


    @ManyToOne
    @JsonProperty("Invoice")
    @JoinColumn(name="InvoiceId")
    private Invoice Invoice;


    @Enumerated(EnumType.STRING)
    @Column(name="EcheanceStatus")
    public EcheanceStatus echeanceStatus;







}










