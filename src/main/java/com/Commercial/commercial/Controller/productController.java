package com.Commercial.commercial.Controller;

import com.Commercial.commercial.Constants.CONSTANTS;
import com.Commercial.commercial.DAO.ProductDTO;
import com.Commercial.commercial.DAO.product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Commercial.commercial.Service.productService;

import java.util.List;

@RestController
@RequestMapping(path="/products")
@RequiredArgsConstructor
public class productController {
    public final productService productService;

    @PostMapping(path="/createProduct")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO prd) throws Exception {
       try {
           ProductDTO created= productService.createProduct(prd);
           return new ResponseEntity<>(created, HttpStatus.OK);
       }
       catch(Exception e){
           throw new Exception(e);       }
    }

    @DeleteMapping(path="/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id)  {

             String responseMessage=productService.deleteProduct(id);
             if(responseMessage=="Product deleted successfully"){
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        }
             else
              return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);

    }

    @PutMapping(path="/updateProduct/{id}")
    public ResponseEntity<product> updateProduct(@PathVariable Integer id,@RequestBody product prd)  {

       product updatingProduct= productService.updateProduct(id,prd);
       return new ResponseEntity<>(updatingProduct, HttpStatus.OK);

    }
    @GetMapping(path="/getAllProducts")
    public List<ProductDTO> getAllProducts()  {

      return  productService.getAllProducts();
    }

    @GetMapping(path="/getProduct/{id}")
    public ProductDTO getProduct(@PathVariable Integer id)  {

        return  productService.getProduct(id);
    }

}
