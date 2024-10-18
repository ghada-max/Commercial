package com.Commercial.commercial.Service;

import com.Commercial.commercial.Constants.paymentStatus;
import com.Commercial.commercial.DAO.Echeance;
import com.Commercial.commercial.DAO.Invoice;
import com.Commercial.commercial.repository.InvoiceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;


@RequiredArgsConstructor
@Service
public class InvoicePaymenService {
    public final InvoiceService invSer;

    @Autowired
    InvoiceRepository invRepo;

    public Invoice SetPaymentStatus(Integer id) throws Exception {

        List<Echeance> echeances = invSer.getAllEcheanceByInvoiceId(id);
        int paidCount = (int) echeances.stream()
                .filter(e -> e.getEcheanceStatus()
                        .getLabel().equalsIgnoreCase("payed")).count();

        int TotalEcheances = echeances.size();
        return invRepo.findById(id).map(invoice -> {
            String ammountPaid = paidCount + "/" + TotalEcheances;
            if (echeances.isEmpty()) {
                throw new IllegalStateException("this invoice doesnt contain echeances, its paid on receipt");
            }
            if((paidCount<TotalEcheances) && (paidCount)!=0){
                invoice.setPaymentStatus(paymentStatus.PARTIALLY_PAYED);
            }else if(paidCount==TotalEcheances){
                invoice.setPaymentStatus(paymentStatus.PAYED);
            }
            invoice.setAmmountPaid(ammountPaid);
            return invRepo.save(invoice);

        }).orElseThrow(() -> new EntityNotFoundException("No Invoice found with the given ID, please try again."));
    }


}

//payment Satus
//paymentAmmount
