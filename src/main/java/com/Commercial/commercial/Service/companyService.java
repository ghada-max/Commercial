package com.Commercial.commercial.Service;

import com.Commercial.commercial.DAO.company;
import com.Commercial.commercial.DAO.client;

import com.Commercial.commercial.repository.companyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class companyService {
    public final ClientService clientService;
    @Autowired
    companyRepository companyRepo;


    public List<client> getAllClients(){
      return clientService.getAllClients();
    }
    public company createCompany(company comp) {
        comp.setCreationDate(new Date());
        comp.setLastModifDate(new Date());
        return   companyRepo.save(comp);
    }

    public company updateCompany(company comp) {
        return companyRepo.findAll().stream().findFirst().map(existingCompany->{
            existingCompany.setAddress(comp.getAddress());
            existingCompany.setCRN(comp.getCRN());
            existingCompany.setEmail(comp.getEmail());
            existingCompany.setPhone(comp.getPhone());
            existingCompany.setDenomination(comp.getDenomination());
            existingCompany.setInChargeOf(comp.getInChargeOf());
            existingCompany.setSpecifiedIn(comp.getSpecifiedIn());
            existingCompany.setFax(comp.getFax());
            existingCompany.setLastModifDate(new Date());
            if (comp.getCreationDate() != null) {
                existingCompany.setCreationDate(comp.getCreationDate());
            }


            companyRepo.save(existingCompany);
            return existingCompany;
        }).orElseThrow(()->new EntityNotFoundException("no company found with Id "));
    }

    public List<company> getCompany() {
      return  companyRepo.findAll();
    }
}
