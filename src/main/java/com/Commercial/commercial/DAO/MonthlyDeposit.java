package com.Commercial.commercial.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="MonthlyDeposit")
public class MonthlyDeposit implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public Integer id;

    @JsonProperty("Deposit") // Change the JSON property name to lowercase
    @Column(name = "Deposit") // You can also specify the column name here
    private BigDecimal Deposit= BigDecimal.ZERO;
}
