package com.Commercial.commercial.Controller;

import com.Commercial.commercial.Constants.CONSTANTS;
import com.Commercial.commercial.Service.SupplierService;
import com.Commercial.commercial.DAO.supplier;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/supplier")
@RequiredArgsConstructor
public class supplierController {
    public final SupplierService supplierService;
    @PostMapping(path="/createSupplier")
    public ResponseEntity<supplier> createSupplier(@RequestBody supplier sup) throws Exception {
        try {
            supplier created= supplierService.createSupplier(sup);
            return new ResponseEntity<>(created, HttpStatus.OK);
        }
        catch(Exception e){
            throw new Exception(CONSTANTS.SOMETHING_WENT_WRONG,e);       }
    }

    @DeleteMapping(path="/deleteSupplier/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Integer id)  {

        String responseMessage=supplierService.deleteSupplier(id);
        if(responseMessage=="Supplier deleted successfully"){
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);

    }

    @PutMapping(path="/updateSupplier/{id}")
    public ResponseEntity<supplier> updateSupplier(@PathVariable Integer id,@RequestBody supplier sup)  {

        supplier updatingSupplier= supplierService.updateSupplier(id,sup);
        return new ResponseEntity<>(updatingSupplier, HttpStatus.OK);

    }
    @GetMapping(path="/getAllSuppliers")
    public List<supplier> getAllSuppliers()  {

        return  supplierService.getAllSuppliers();
    }

    @GetMapping(path="/getSupplierById/{id}")
    public supplier getSupplierById(@PathVariable Integer id)  {

        return  supplierService.getSupplierById(id);
    }

}
