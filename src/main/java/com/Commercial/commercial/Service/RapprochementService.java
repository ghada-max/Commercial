package com.Commercial.commercial.Service;

import com.Commercial.commercial.DAO.Echeance;
import com.Commercial.commercial.DAO.Rapprochement;
import com.Commercial.commercial.Service.PaymentModule.EcheanceService;
import com.Commercial.commercial.repository.EcheanceRepository;
import com.Commercial.commercial.repository.RapprochementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@RequiredArgsConstructor
@Service
public class RapprochementService {
    @Autowired
    EcheanceRepository echRep;

    @Autowired
    RapprochementRepository rapRepo;

   public final  EcheanceService echSer;

   public Rapprochement calculateMonthlyEstimatedRevenu() {
       BigDecimal amount = BigDecimal.ZERO;
       List<Echeance> echeances = getEcheancesForCurrentMonth();
       List<Echeance> result = new ArrayList<>(echeances);  // Create a new list to store results
       Iterator<Echeance> iterator = echeances.iterator();
       while (iterator.hasNext()) {
           Echeance echeance = iterator.next();

           System.out.println(echeance);
           result.add(echeance);
           amount=amount.add(BigDecimal.valueOf(echeance.getAmount()));
       }
     //return amount;
       Rapprochement rap=new Rapprochement();
       rap.setEstimatedMonthlyDeposit(amount);
       rap.setDate(new Date());
       return rapRepo.save(rap);
   }
    public List<Echeance> getEcheancesForCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;  // Months are 0-based, so add 1
        int currentYear = calendar.get(Calendar.YEAR);

        return echRep.findEcheancesWithDueDateInCurrentMonth(currentMonth, currentYear);
    }
    @EventListener
    public void handleEcheancePaidEvent(EcheancePaidEvent event) {
        updateMonthDeposit(event.getAmount());
    }
    public void updateMonthDeposit(BigDecimal amount) {
        Calendar calender=Calendar.getInstance();
        int currentMonth = calender.get(Calendar.MONTH) + 1;  // Months are 0-based, so add 1
        int currentYear=calender.get(Calendar.YEAR);
        Optional<Rapprochement> optionalRap = getCurrentMonthRapprochement();

        if (optionalRap.isPresent()) {
            Rapprochement rap = optionalRap.get();  // Extract the entity
            BigDecimal recentDepositAmount = rap.getMonthDeposit();
            BigDecimal updatedAmount = recentDepositAmount.add(amount);
            rap.setMonthDeposit(updatedAmount);
            rapRepo.save(rap);
        } else {
            System.out.println("No Rapprochement found.");
        }}

    public Optional<Rapprochement> getCurrentMonthRapprochement(){
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;  // Months are 0-based, so add 1
        int currentYear = calendar.get(Calendar.YEAR);

        return  rapRepo.findRapprochementWithDateInCurrentMonth(currentMonth, currentYear);

    }
}
