package com.Commercial.commercial.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Table(name="company")
public class company {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer Id;

    @JsonProperty("CreationDate")
    @Column(name="CreationDate")
    private Date CreationDate;

    @JsonProperty("LastModifDate")
    @Column(name="LastModifDate")
    private Date LastModifDate;

    @JsonProperty("TaxId")
    @Column(name="TaxId")
    private String TaxId;



    @JsonProperty("denomination")
    @Column(name="denomination")
    private String denomination;




    @JsonProperty("inChargeOf")
    @Column(name="inChargeOf")
    private String inChargeOf;



    @JsonProperty("SpecifiedIn")
    @Column(name="SpecifiedIn")
    private String SpecifiedIn;

    @JsonProperty("CRN")
    @Column(name="CRN")
    private String CRN;

    @JsonProperty("address")
    @Column(name="address")
    private String address;

    @JsonProperty("phone")
    @Column(name="phone")
    private String phone;

    @JsonProperty("imageURL")
    @Column(name="imageURL")
    private String imageURL;

    @JsonProperty("fax")
    @Column(name="fax")
    private String fax;

    @JsonProperty("email")
    @Column(name="email")
    private String email;

    @JsonProperty("BankAccountId")
    @OneToOne
    @JoinColumn(name = "BankAccountId", unique = true)
    private BankAccount bankAccount;

    @JsonProperty("Balance")
    @Column(name="Balance")
    private BigDecimal Balance = BigDecimal.ZERO;;


}
