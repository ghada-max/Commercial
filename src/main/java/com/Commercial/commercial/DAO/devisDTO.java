package com.Commercial.commercial.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class devisDTO {


        private Integer Id;

        private client client;

        private category category;

        private Integer sum;

        private Integer productQuantityD;

        private Date creationDate;

        private Date offerEndDate;

        private String Currency;


        private Integer discount;
        private List<ProductDTO> productsDto;


    }











