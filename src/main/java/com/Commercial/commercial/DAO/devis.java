package com.Commercial.commercial.DAO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Devis")
public class devis  implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @JsonProperty("Sum")
    @Column(name="Sum")
    private Integer sum;

    @JsonProperty("productQuantityD")
    @Column(name="productQuantityD")
    private Integer productQuantityD;

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



    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name="devis_products",
    joinColumns = @JoinColumn(name="devis_id"),
    inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<product> products; // Change to Product



}









