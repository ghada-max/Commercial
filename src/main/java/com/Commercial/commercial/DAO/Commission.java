package com.Commercial.commercial.DAO;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="commission")
public class Commission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @JsonProperty("Amount")
    @Column(name="Amount")
    private Float Amount;

    @JsonProperty("CommissionAmmount")
    @Column(name="CommissionAmmount")
    private Float CommissionAmmount;

    @JsonProperty("Description")
    @Column(name="Description")
    private Float Description;


}
