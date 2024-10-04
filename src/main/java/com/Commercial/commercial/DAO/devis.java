package com.Commercial.commercial.DAO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Devis")
public class devis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonProperty("clientId")
    @JoinColumn(name="clientId",nullable = false)
    private client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonProperty("categoryId")
    @JoinColumn(name="categoryId",nullable=false)
    private category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonProperty("productId")
    @JoinColumn(name="productId",nullable=false)
    private product product;

    @JsonProperty("Sum")
    @Column(name="Sum")
    private Integer sum;


    @JsonProperty("creationDate")
    @Column(name="creationDate")
    private Date creationDate;

    @JsonProperty("offerEndDate")
    @Column(name="offerEndDate")
    private Date offerEndDate;

    @JsonProperty("currency")
    @Column(name="currency")
    private String Currency;

    @JsonProperty("discount")
    @Column(name="discount")
    private Integer discount;


}









