package com.Commercial.commercial.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name="BankAccount")
public class BankAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column(name="BankName")
    @JsonProperty("BankName")
    private String BankName;

    @Column(name="AccountNumber")
    @JsonProperty("AccountNumber")
    private String AccountNumber;

    @Column(name="Balance")
    @JsonProperty("Balance")
    private Float Balance;

}
