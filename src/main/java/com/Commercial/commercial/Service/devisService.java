package com.Commercial.commercial.Service;
import com.Commercial.commercial.Constants.devisStatus;
import com.Commercial.commercial.DAO.*;
import com.Commercial.commercial.repository.ClientRepository;
import com.Commercial.commercial.repository.devisRepository;
import com.Commercial.commercial.repository.productRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.Commercial.commercial.Constants.devisStatus.*;

@RequiredArgsConstructor
@Service
public class devisService{
    public final productService proSer;
    @Autowired
    devisRepository devisRepo;
    @Autowired
    ClientRepository clientRepo;

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

    public devisDTO createDevis(devis devi) {
        devi.setCreationDate(new Date());

        Calendar cal = Calendar.getInstance();
        cal.setTime(devi.getCreationDate());
        cal.add(Calendar.DAY_OF_MONTH, 30);
        devi.setOfferEndDate(cal.getTime());

        // Fetch and attach client
        client client = clientRepo.findById(devi.getClient().getId())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
        devi.setClient(client);

        int totalSum = 0;
        List<product> attachedProducts = new ArrayList<>();

        // Fetch and attach products
        for (product prod : devi.getProducts()) {
            product fetchedProduct = productRepo.findById(prod.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));

            // Set the ordered quantity
          //  fetchedProduct.setOrderedQuantity(prod.getOrderedQuantity());
           // attachedProducts.add(fetchedProduct);

            totalSum += fetchedProduct.getPrice() * prod.getOrderedQuantity();
        }
        System.out.println("Total calculated before discount: " + totalSum);

     //   devi.setProducts(attachedProducts);

        devi.setDevisStatus(PENDING);
        devi.setSum(totalSum);

         devis d= devisRepo.save(devi);
         return convertDevis(d);
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


    public void validation(devis dev){
        dev.setDevisStatus(ACCEPTED);
        devisRepo.save(dev);
    }

}
