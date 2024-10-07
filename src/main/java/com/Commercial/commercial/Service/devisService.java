package com.Commercial.commercial.Service;
import com.Commercial.commercial.DAO.*;
import com.Commercial.commercial.repository.devisRepository;
import com.Commercial.commercial.repository.productRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class devisService{
    public final productService proSer;
    @Autowired
    devisRepository devisRepo;

    @Autowired
    productRepository productRepo;

    public ProductDTO convertToDto(product prod){
      return  proSer.convertToDto(prod);
    }
    public devisDTO convertDevis(devis dev){
        devisDTO devDTO=new devisDTO();
        devDTO.setId(dev.getId());

        devDTO.setClient(dev.getClient());
        devDTO.setCurrency(dev.getCurrency());
        devDTO.setProductQuantityD(dev.getProductQuantityD());
        devDTO.setCreationDate(dev.getCreationDate());
        devDTO.setOfferEndDate(dev.getOfferEndDate());
        devDTO.setSum(dev.getSum());

        List<product> prod=dev.getProducts();

       List<ProductDTO> prodsDto=prod.stream().map(this::convertToDto)
              .collect(Collectors.toList());
        devDTO.setProductsDto( prodsDto);


        devDTO.setDiscount(dev.getDiscount());
        return devDTO;
    }

    public devis createDevis(devis devi) {
        devi.setCreationDate(new Date());
        Calendar cal = Calendar.getInstance();
        cal.setTime(devi.getCreationDate());  // Set the calendar to the creation date
            cal.add(Calendar.DAY_OF_MONTH, 30);   // Add 30 days to the creation date
        devi.setOfferEndDate(cal.getTime());  // Set the offerEndDate

        for (product product : devi.getProducts()) {
            product fetchedProduct = productRepo.findById(product.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        }


        int totalSum = 0;
        for (product product : devi.getProducts()) {
            totalSum += product.getPrice() * product.getOrderedQuantity(); // Assuming you have quantity in Product
        }
        devi.setSum(totalSum);

        return  devisRepo.save(devi);
    }

    public List<devisDTO> getAllDevis() {
        List<devis> Ldevis= devisRepo.findAll();
        return Ldevis.stream().map(this::convertDevis).collect(Collectors.toList());
    }

    public devisDTO getDeviById(Integer id) {
        devis dev= devisRepo.findById(id).orElseThrow(()->new EntityNotFoundException("no devis found with id :"+id +"."));
       return convertDevis(dev);
    }

    public String deleteDeviById(Integer id) {

        return devisRepo.findById(id).map(devis->{
            devisRepo.deleteById(id);
            return ("devis with id "+id+" deleted successfully");
        }).orElseThrow(()->new EntityNotFoundException("no devis found with id :"+id +".")
        );    }
}
