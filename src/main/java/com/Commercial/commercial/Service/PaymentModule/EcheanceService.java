package com.Commercial.commercial.Service.PaymentModule;

import com.Commercial.commercial.Constants.EcheanceStatus;
import com.Commercial.commercial.DAO.Rapprochement;
import com.Commercial.commercial.DAO.company;
import com.Commercial.commercial.Service.EcheancePaidEvent;
import com.Commercial.commercial.Service.EmailSender.EmailSenderService;
import com.Commercial.commercial.Service.RapprochementService;
import com.Commercial.commercial.Service.companyService;
import com.Commercial.commercial.repository.EcheanceRepository;
import com.Commercial.commercial.repository.companyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EcheanceService {

    private final EcheanceRepository echRep;
    //private final RapprochementService rapService;  // @Lazy applied during injection via constructor
    private final companyService compSer;
    private final EmailSenderService MailSender;
    private final companyRepository compRep;
    private final ApplicationEventPublisher eventPublisher;

    String message;

    public LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }


    public void updateCompanyBalance(BigDecimal amount) {
        company comp = compRep.findAll().stream().findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));
        BigDecimal updatedBalance = comp.getBalance().add(amount);
        comp.setBalance(updatedBalance);
        compRep.save(comp);
    }

    @Transactional
    public synchronized String updateStatusToPaid(Integer id) {
        return echRep.findById(id).map(echeance->{
            if (echeance.getEcheanceStatus() == EcheanceStatus.PAYED) {
                return "Echeance already paid.";
            }
            echeance.setEcheanceStatus(EcheanceStatus.PAYED);
            echRep.save(echeance);
            updateCompanyBalance(BigDecimal.valueOf(echeance.getAmount()));
            BigDecimal amount = BigDecimal.valueOf(echeance.getAmount());

            //  rapSer.updateMonthDeposit(BigDecimal.valueOf(echeance.getAmount()));
            eventPublisher.publishEvent(new EcheancePaidEvent(this, amount));

            System.out.println(echeance);
            return ("Echeance Status Updated");
        }).orElseThrow(()->new EntityNotFoundException("Echeance with id not found"));
    }

    public Void sendEmail() {
        echRep.findAll().forEach(echeance -> {
            String ClientEmail=echeance.getInvoice().getInvoiceDetails().getClient().getEmail();

            Calendar calendar = Calendar.getInstance();

            // Get due date and convert it to LocalDate
            Date dueDate = echeance.getDueDate();
            LocalDate dueDateLocal = convertToLocalDate(dueDate);
            LocalDate reminderDate = dueDateLocal.minusDays(7);

            // Calculate the difference in days
            long daysDifference = ChronoUnit.DAYS.between(convertToLocalDate(new Date()), dueDateLocal);
                  // Check if the difference is more than 7 days
            if (daysDifference >7 && echeance.getEcheanceStatus().getLabel().equalsIgnoreCase("PENDING")) {
                System.out.println("The due date is more than 7 days.");
                // MailSender.sendEmail(ClientEmail,message,message);

                System.out.println(ClientEmail);
            }
               else  if (daysDifference == 7 && echeance.getEcheanceStatus().getLabel().equalsIgnoreCase("PENDING")) {
                this.message="The due date is within 7 days.";
                String name=echeance.getInvoice().getInvoiceDetails().getClient().getName();
                String email=echeance.getInvoice().getInvoiceDetails().getClient().getEmail();
                String phone=echeance.getInvoice().getInvoiceDetails().getClient().getPhone();
                String address=echeance.getInvoice().getInvoiceDetails().getClient().getAddress();
                float ammount=echeance.getAmount();
                //ajouter les produit de la liste
                String EmailText="Alert "+message+"\n Client Name:"+name+"\n Email:"+email+"\n Phone:"+phone+"\n Address:"+address+"\n Ammount:"+ammount;
                MailSender.sendEmail(ClientEmail,message,EmailText);
                System.out.println(ClientEmail);

                System.out.println("The due date is within 7 days.");
            }
            else  if (daysDifference == -1 && echeance.getEcheanceStatus().getLabel().equalsIgnoreCase("PENDING")) {
                echeance.setEcheanceStatus(EcheanceStatus.NON_PAYED);
                this.message = "The due date is expired.";
                String name = echeance.getInvoice().getInvoiceDetails().getClient().getName();
                String email = echeance.getInvoice().getInvoiceDetails().getClient().getEmail();
                String phone = echeance.getInvoice().getInvoiceDetails().getClient().getPhone();
                String address = echeance.getInvoice().getInvoiceDetails().getClient().getAddress();
                float ammount = echeance.getAmount();
                //ajouter les produit de la liste
                String EmailText = "Alert " + message + "\n Client Name:" + name + "\n Email:" + email + "\n Phone:" + phone + "\n Address:" + address + "\n Ammount:" + ammount;
                MailSender.sendEmail(ClientEmail, message, EmailText);
                System.out.println(ClientEmail);

                System.out.println("The due date is within 7 days.");
                 echRep.save(echeance);
            }
        });
        return null;
    }

   }



    //changer les status ,les methode
    //crer les methodes de paiement qun client peut choisir
    //REGLER LES FACTURE A PAIEMENT DANS LA RECEPTION

