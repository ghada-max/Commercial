package com.Commercial.commercial.Controller;

import com.Commercial.commercial.Constants.EcheanceStatus;
import com.Commercial.commercial.Constants.EcheanceStatusDTO;
import com.Commercial.commercial.DAO.Echeance;
import com.Commercial.commercial.DAO.Invoice;
import com.Commercial.commercial.Service.InvoiceService;

import com.Commercial.commercial.Service.PaymentModule.EcheanceService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/Echeance")
public class EcheanceController {
    public final InvoiceService invSer;
    public final EcheanceService echSer;
    //public final EcheanceStatus EcheanceStatus;



    @PostMapping(path = "/createEcheancesFromInvoice")
    public ResponseEntity<List<Echeance>> createEcheancesFromInvoice(@RequestBody Invoice inv) throws Exception {
        try {
            List<Echeance> echenaces = invSer.createEcheancesFromInvoice(inv);
            return new ResponseEntity<>(echenaces, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @PutMapping(path="/updateEcheanceStatus/{id}")
     public ResponseEntity<String> updateEcheanceStatus(@PathVariable Integer id,@RequestBody EcheanceStatusDTO statusDTO)throws Exception
    {
        try{
            EcheanceStatus echStatus = statusDTO.getEcheanceStatus();

            String response=echSer.updateStatus(id,echStatus);
            return new  ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            throw new Exception(e);
        }
    }
    @GetMapping(path="/sendEmailReminder")
    public ResponseEntity<Void> SendEmailReminder(){
        Void response=echSer.sendEmail();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}