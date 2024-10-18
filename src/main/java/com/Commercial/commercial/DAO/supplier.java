package com.Commercial.commercial.DAO;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Table(name="supplier")
@Entity
public class supplier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Or GenerationType.AUTO
    @Column
    private Integer id;

    @JsonProperty("TaxId")
    @Column(name="TaxId")
    private String TaxId;

    @JsonProperty("name") // Change "TTC" to "ttc" in JSON
    @Column
    private String name;

    @JsonProperty("email") // Change "TTC" to "ttc" in JSON
    @Column
    private String email;

    @JsonProperty("phone") // Change "TTC" to "ttc" in JSON
    @Column
    private String phone;

    @JsonProperty("fax") // Change "TTC" to "ttc" in JSON
    @Column
    private String fax;

    @JsonProperty("address") // Change "TTC" to "ttc" in JSON
    @Column
    private String address;

    @JsonProperty("bankAccount") // Change "TTC" to "ttc" in JSON
    @Column
    private String bankAccount;
















}

