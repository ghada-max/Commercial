package com.Commercial.commercial.DAO;
import com.Commercial.commercial.Constants.EcheanceStatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
public class EcheanceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date EmissionDate;

    private Date DueDate;

    private Float Amount;

    private InvoiceDTO InvoiceDTO;

    public EcheanceStatus EcheanceStatus;



}










