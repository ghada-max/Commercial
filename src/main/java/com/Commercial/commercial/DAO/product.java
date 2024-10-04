package com.Commercial.commercial.DAO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="products")
public class product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @JsonProperty("name") // Change the JSON property name to lowercase
    @Column(name = "Name") // You can also specify the column name here
    private String Name;

    @ManyToOne
    @JsonProperty("category") // Change "Details" to "details" in JSON
    @JoinColumn(name = "category_id",nullable=false)
    private category category;


    @JsonProperty("designation")
    @Column
    private String designation;

    @JsonProperty("details") // Change "Details" to "details" in JSON
    @Column(name = "Details") // Keeping the original column name
    private String Details;

    @JsonProperty("image")
    @Column
    private String image;

    @JsonProperty("quantity")
    @Column
    private Integer quantity;

    @JsonProperty("price")
    @Column
    private Integer price;

    @JsonProperty("htt") // Change "HTT" to "htt" in JSON
    @Column(name = "HTT")
    private Integer HTT;

    @JsonProperty("ttc") // Change "TTC" to "ttc" in JSON
    @Column(name = "TTC")
    private Integer TTC;


}
