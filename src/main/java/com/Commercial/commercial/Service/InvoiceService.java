package com.Commercial.commercial.Service;

import com.Commercial.commercial.Constants.EcheanceStatus;
import com.Commercial.commercial.DAO.*;

import com.Commercial.commercial.repository.EcheanceRepository;
import com.Commercial.commercial.repository.InvoiceRepository;
import com.Commercial.commercial.repository.devisRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.Commercial.commercial.Constants.paymentStatus.*;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepo;
    @Autowired
    EcheanceRepository echRep;
    @Autowired
    devisService devisServ;
    @Autowired
    devisRepository deviRep;

    public InvoiceDTO convertInvoiceToInvoiceDTO(Invoice convertedInvoice){
        InvoiceDTO invDTO=new InvoiceDTO();
        devis dev=new devis();
        devisDTO   devDTO=devisServ.convertDevis(dev);
        invDTO.setInvoiceDetails(devDTO);
        invDTO.setEmissionDate(convertedInvoice.getEmissionDate());
        invDTO.setDueDate(convertedInvoice.getDueDate());
        invDTO.setMonthNumber(convertedInvoice.getMonthNumber());
        invDTO.setPaymentMethod(convertedInvoice.getPaymentMethod());
        invDTO.setPaymentStatus(convertedInvoice.getPaymentStatus());
        return invDTO;
    }
    public Invoice AddInvoice(Invoice inv) throws IllegalArgumentException{
        return deviRep.findById(inv.getInvoiceDetails().getId()).map(devis -> {
            if (devis.getDevisStatus().getLabel().equalsIgnoreCase("accepted")) {

                inv.setEmissionDate(new Date());
                inv.setPaymentStatus(PENDING);
                inv.setMonthNumber(inv.getMonthNumber());
                inv.setPaymentMethod(inv.getPaymentMethod());
                inv.setDeliveryStatus(false);
                if (inv.getPaymentMethod().getLabel().equalsIgnoreCase("payment on receipt")){
                    inv.setDueDate(inv.getDueDate());
                    inv.setMonthNumber(null);
                }
                else{
                    inv.setDueDate(null);
                }
               return invoiceRepo.save(inv);
              // System.out.println(devis.getDevisStatus().);
            }
            else {
                throw new IllegalArgumentException("devis status is not yet accepted");
            }
        }).orElseThrow(()->new EntityNotFoundException("devis not Found"));
    }
    public InvoiceDTO getInvoiceById(Integer id) {
        return invoiceRepo.findById(id).map(Invoice ->{

            InvoiceDTO InvDTO=new InvoiceDTO();

          devis dev=  Invoice.getInvoiceDetails();
          devisDTO devDTO=devisServ.convertDevis(dev);
          InvDTO.setId(Invoice.getId());
            InvDTO.setInvoiceDetails(devDTO);
            InvDTO.setDueDate(Invoice.getDueDate());
            InvDTO.setPaymentStatus(Invoice.getPaymentStatus());
            InvDTO.setPaymentMethod(Invoice.getPaymentMethod());

          return InvDTO;
        }).orElseThrow(()-> new EntityNotFoundException("Invoice with id:"+id+" not found"));
    }


    public List<Echeance> createEcheancesFromInvoice(Invoice inv) throws EntityNotFoundException, IllegalArgumentException {
        return invoiceRepo.findById(inv.getId()).map(invoIce -> {
            List<Echeance> echeances = new ArrayList<>();
            if (invoIce.getPaymentMethod().getLabel().equalsIgnoreCase("payment on receipt")) {
                Echeance ech=new Echeance();
                Calendar calendar = Calendar.getInstance();
                float totalAmount = invoIce.getInvoiceDetails().getSum();

                    float amountPerEcheance = totalAmount ;


                        calendar.setTime(new Date());
                        ech.setEmissionDate(calendar.getTime());

                        calendar.add(Calendar.MONTH, 1); // Set due date 1 month after emission
                        ech.setDueDate(calendar.getTime());

                        ech.setInvoice(invoIce);
                        ech.setAmount(amountPerEcheance);
                        ech.setEcheanceStatus(EcheanceStatus.PENDING);
                        echRep.save(ech);
                        echeances.add(ech);
            }
            if (invoIce.getPaymentMethod().getLabel().equalsIgnoreCase("payment schedule")) {

                Calendar calendar = Calendar.getInstance();
                float totalAmount = invoIce.getInvoiceDetails().getSum();

                // Check for "On 3 Month" schedule
                if (invoIce.getMonthNumber().getLabel().equalsIgnoreCase("On 3 Month")) {
                    float amountPerEcheance = totalAmount / 3;

                    for (int i = 0; i < 3; i++) {
                        Echeance ech = new Echeance();
                        calendar.setTime(new Date());
                        calendar.add(Calendar.MONTH, i); // Increment months
                        ech.setEmissionDate(calendar.getTime());

                        calendar.add(Calendar.MONTH, 1); // Set due date 1 month after emission
                        ech.setDueDate(calendar.getTime());

                        ech.setInvoice(invoIce);
                        ech.setAmount(amountPerEcheance);
                        ech.setEcheanceStatus(EcheanceStatus.PENDING);
                        echeances.add(ech);
                        echRep.save(ech);
                    }
                }
                // Check for "On 6 Month" schedule
                else if (invoIce.getMonthNumber().getLabel().equalsIgnoreCase("On 6 Month")) {
                    float amountPerEcheance = totalAmount / 6;

                    for (int i = 0; i < 6; i++) { // Loop runs 6 times for 6 months
                        Echeance ech = new Echeance();
                        calendar.setTime(new Date());
                        calendar.add(Calendar.MONTH, i); // Increment months
                        ech.setEmissionDate(calendar.getTime());

                        calendar.add(Calendar.MONTH, 1); // Set due date 1 month after emission
                        ech.setDueDate(calendar.getTime());

                        ech.setInvoice(invoIce);
                        ech.setAmount(amountPerEcheance);
                        ech.setEcheanceStatus(EcheanceStatus.PENDING);

                        echeances.add(ech);
                        echRep.save(ech);
                    }
                }
            }
            return echeances;
        }).orElseThrow(() -> new EntityNotFoundException("Invoice not found with id: " + inv.getId()));
    }

    public List<Echeance> getAllEcheanceByInvoiceId(Integer id) {
        List <Echeance> echeances=echRep.findByInvoiceId(id);

        return echeances;
    }
}
