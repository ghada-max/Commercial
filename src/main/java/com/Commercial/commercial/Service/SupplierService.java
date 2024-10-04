package com.Commercial.commercial.Service;

import com.Commercial.commercial.DAO.supplier;
import com.Commercial.commercial.repository.CategoryRepository;
import com.Commercial.commercial.repository.supplierRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    supplierRepository supplierRepo;
    @Autowired
    CategoryRepository categoryRep;
    public supplier createSupplier(supplier sup) {

        if (supplierRepo.findByEmail(sup.getEmail()).isPresent()) {
            // Email is already present, return error message or handle accordingly
            throw new IllegalArgumentException("Email already exists. Please enter another one.");
        } else {
            // Email is not present, save the supplier
            return supplierRepo.save(sup);
        }
    }

    public String deleteSupplier(Integer id) {

        return  supplierRepo.findById(id).map(supplier -> {
            supplierRepo.deleteById(id);
            return "supplier deleted successfully";
        }).orElse("supplier with id "+id+" not found");
    }

    public supplier updateSupplier(Integer id, supplier sup) {
            return supplierRepo.findById(id).map(updatingSupplier -> {
                updatingSupplier.setName(sup.getName());
                updatingSupplier.setAddress(sup.getAddress());
                updatingSupplier.setEmail(sup.getEmail());

                updatingSupplier.setFax(sup.getFax());
                updatingSupplier.setPhone(sup.getPhone());
                updatingSupplier.setBankAccount(sup.getBankAccount());
                supplierRepo.save(updatingSupplier);
                return updatingSupplier;
            }).orElseThrow(() -> new EntityNotFoundException("supplier not found while updating"));

    }

    public List<supplier> getAllSuppliers() {
        return supplierRepo.findAll();
    }

    public supplier getSupplierById(Integer id) {
        return supplierRepo.findById(id).orElseThrow(()->new EntityNotFoundException("supplier with id "+id+" not found"));
    }
}
