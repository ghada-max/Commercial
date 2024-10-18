package com.Commercial.commercial.Controller;


import com.Commercial.commercial.Constants.PaymentStatusDTO;
import com.Commercial.commercial.Constants.paymentStatus;
import com.Commercial.commercial.DAO.Invoice;
import com.Commercial.commercial.Service.InvoicePaymenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping(path="/InvoicePaymentManager")
public class InvoicePaymenServiceController{
    public final InvoicePaymenService invoicePaySer;

    @PutMapping(path="/SetPaymentStatus/{id}")
    public Invoice SetPaymentStatus(@PathVariable Integer id)
    {  try{
        Invoice response=invoicePaySer.SetPaymentStatus(id);
        return new ResponseEntity<>(response,HttpStatus.OK).getBody();
    }
    catch (Exception e){
        throw new RuntimeException("Error updating payment status: " + e.getMessage(), e);
    }

    }




}
