package com.Commercial.commercial.Service;

import com.Commercial.commercial.DAO.ProductDTO;
import com.Commercial.commercial.DAO.product;
import com.Commercial.commercial.repository.CategoryRepository;
import com.Commercial.commercial.repository.productRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class productService {
    @Autowired
    productRepository productRepo;
    @Autowired
    CategoryRepository categoryRep;



    public ProductDTO convertToDto(product prod) {
        ProductDTO dto = new ProductDTO();
        dto.setId(prod.getId());
        dto.setName(prod.getName());
        dto.setDesignation(prod.getDesignation());
        dto.setCategory(prod.getCategory());
        dto.setDetails(prod.getDetails());
        dto.setImage(prod.getImage());
        dto.setQuantity(prod.getQuantity());
        dto.setOrderedQuantity(prod.getOrderedQuantity());
        dto.setPrice(prod.getPrice());
        dto.setHtt(prod.getHTT());
        dto.setTtc(prod.getTTC());
        return dto;
    }
    public ProductDTO createProduct(ProductDTO prdDto) {
        // Convert ProductDTO to product entity
        product newProduct = new product();
        newProduct.setName(prdDto.getName());
        newProduct.setDesignation(prdDto.getDesignation());
        newProduct.setCategory(prdDto.getCategory());

        newProduct.setDetails(prdDto.getDetails());
        newProduct.setImage(prdDto.getImage());
        newProduct.setQuantity(prdDto.getQuantity());
        newProduct.setOrderedQuantity(prdDto.getOrderedQuantity());
        newProduct.setPrice(prdDto.getPrice());
        newProduct.setHTT(prdDto.getHtt());
        newProduct.setTTC(prdDto.getTtc());

        // Save the new product entity to the repository
        product savedProduct = productRepo.save(newProduct);

        // Convert saved product back to ProductDTO and return it
        return convertToDto(savedProduct);
    }


    public String deleteProduct(Integer id) {

      return  productRepo.findById(id).map(product -> {
            productRepo.deleteById(id);
            return "Product deleted successfully";
        }).orElse("product with id "+id+" not found");
    }

    public product updateProduct(Integer id, product prd) {
        return categoryRep.findById(prd.getCategory().getId()).map(category-> {
            return productRepo.findById(id).map(updatingproduct -> {
                updatingproduct.setName(prd.getName());
                updatingproduct.setCategory(prd.getCategory());
                updatingproduct.setDetails(prd.getDetails());
                updatingproduct.setPrice(prd.getPrice());
                updatingproduct.setQuantity(prd.getQuantity());
                updatingproduct.setImage(prd.getImage());
                updatingproduct.setDesignation(prd.getDesignation());
                updatingproduct.setTTC(prd.getTTC());
                updatingproduct.setHTT(prd.getHTT());
                productRepo.save(updatingproduct);
                return updatingproduct;
            }).orElseThrow(() -> new EntityNotFoundException("product not found while updating"));
        }).orElseThrow(() -> new EntityNotFoundException("category not found while updating"));

    }

    public List<ProductDTO> getAllProducts() {
     List<product> products= productRepo.findAll();
     return products.stream().map(this::convertToDto)
             .collect(Collectors.toList());
    }

    public ProductDTO getProduct(Integer id) {
      product prod=productRepo.findById(id).orElseThrow(()->new EntityNotFoundException("product with id "+id+" not found"));

      return convertToDto(prod);
    }
}
