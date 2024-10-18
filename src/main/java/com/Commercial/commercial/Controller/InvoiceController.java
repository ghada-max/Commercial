package com.Commercial.commercial.Controller;

import com.Commercial.commercial.DAO.*;
import com.Commercial.commercial.Service.InvoiceService;
import com.Commercial.commercial.Service.generateInvoicePdf;

import com.Commercial.commercial.repository.InvoiceRepository;
import com.Commercial.commercial.repository.companyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/Invoice")
public class InvoiceController {
    @Autowired
    InvoiceRepository invRepo;
    @Autowired
    companyRepository companyRepo;
    public final InvoiceService InvoiceServ;
    public final generateInvoicePdf generateInvoiceS;
    @PostMapping(path="/AddInvoice")
    public ResponseEntity<Invoice> AddInvoice(@RequestBody Invoice inv) throws Exception {
     try{   Invoice invoice= InvoiceServ.AddInvoice(inv);
        return new ResponseEntity<>(invoice,HttpStatus.OK);
    }catch (Exception e){
        throw new Exception(e);
    }
    }

    @GetMapping(path="/getInvoiceById/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Integer id)
    {
        InvoiceDTO invoice= InvoiceServ.getInvoiceById(id);
        return new ResponseEntity<>(invoice,HttpStatus.OK);
    }


    @GetMapping("/Invoice/{id}/pdf")
    public ResponseEntity<InputStreamResource> generatePdf(@PathVariable Integer id) throws MalformedURLException {
        Invoice inv=invRepo.findById(id).orElseThrow(()->new EntityNotFoundException("invoice not found"));
        InvoiceDTO invDto=InvoiceServ.getInvoiceById(id);
        //Invoice inv=new InvoiceDTO();

        Optional<company> comp=companyRepo.findAll().stream().findFirst();
        ByteArrayInputStream bis=generateInvoiceS.generatePdf(invDto, comp.orElse(null));
        HttpHeaders header=new HttpHeaders();
        header.add("Content-Disposition","Inline; filename=invoice_"+ id +".pdf");
        return ResponseEntity.ok()
                .headers(header)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }


    @GetMapping("/getAllEcheanceByInvoiceId/{id}")
    public ResponseEntity<List<Echeance>> getAllEcheanceByInvoiceId(@PathVariable Integer id) throws Exception {
        try{
         List<Echeance> echeances=InvoiceServ.getAllEcheanceByInvoiceId(id);
         return new ResponseEntity<>(echeances,HttpStatus.OK);}
        catch (Exception e){
            throw new Exception(e);}
    }
}
