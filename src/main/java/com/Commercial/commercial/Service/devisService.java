package com.Commercial.commercial.Service;

import com.Commercial.commercial.DAO.category;
import com.Commercial.commercial.DAO.client;
import com.Commercial.commercial.DAO.devis;
import com.Commercial.commercial.DAO.product;
import com.Commercial.commercial.repository.devisRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class devisService{
    @Autowired
    devisRepository devisRepo;


    public devis createDevis(devis devi) {
        devi.setCreationDate(new Date());
        Calendar cal = Calendar.getInstance();
        cal.setTime(devi.getCreationDate());  // Set the calendar to the creation date
        cal.add(Calendar.DAY_OF_MONTH, 30);   // Add 30 days to the creation date
        devi.setOfferEndDate(cal.getTime());  // Set the offerEndDate
        return  devisRepo.save(devi);
    }


    public List<devis> getAllDevis() {
        return  devisRepo.findAll();
    }

    public devis getDeviById(Integer id) {
        return devisRepo.findById(id).orElseThrow(()->new EntityNotFoundException("no devis found with id :"+id +".")
        );    }

    public String deleteDeviById(Integer id) {

        return devisRepo.findById(id).map(devis->{
            devisRepo.deleteById(id);
            return ("devis with id "+id+" deleted successfully");
        }).orElseThrow(()->new EntityNotFoundException("no devis found with id :"+id +".")
        );    }
}
