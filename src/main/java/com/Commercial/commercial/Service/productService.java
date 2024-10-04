package com.Commercial.commercial.Service;

import com.Commercial.commercial.DAO.product;
import com.Commercial.commercial.repository.CategoryRepository;
import com.Commercial.commercial.repository.productRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productService {
    @Autowired
    productRepository productRepo;
    @Autowired
    CategoryRepository categoryRep;
    public product createProduct(product prd) {

       productRepo.save(prd);
       return prd;
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

    public List<product> getAllProducts() {
     return productRepo.findAll();
    }

    public product getProduct(Integer id) {
     return productRepo.findById(id).orElseThrow(()->new EntityNotFoundException("product with id "+id+" not found"));
    }
}
