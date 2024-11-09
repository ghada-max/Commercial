package com.Commercial.commercial.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Rapprochement")
public class Rapprochement implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public Integer id;


    @JsonProperty("EstimatedDeposit")
    @Column(name = "EstimatedDeposit")
    private BigDecimal EstimatedMonthlyDeposit;


    @JsonProperty("monthDeposit")
    @Column(name = "monthDeposit")
    private BigDecimal monthDeposit = BigDecimal.ONE;

    @Column(name = "date")
    private Date date;


}
