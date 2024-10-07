package com.Commercial.commercial.DAO;

import lombok.Data;

@Data
public class ProductDTO {
    private Integer id;
    private String name;
    private category category;

    private String designation;
    private String details;
    private String image;
    private Integer quantity;
    private Integer orderedQuantity;
    private Integer price;
    private Integer htt;
    private Integer ttc;
}
