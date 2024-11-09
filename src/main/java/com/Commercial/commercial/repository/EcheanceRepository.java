package com.Commercial.commercial.repository;

import com.Commercial.commercial.DAO.Echeance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EcheanceRepository extends JpaRepository<Echeance,Integer> {
    @Query("SELECT e FROM Echeance e WHERE e.Invoice.id = :id")
    List<Echeance> findByInvoiceId(@Param("id") Integer invoiceId);

    @Query("SELECT e FROM Echeance e WHERE FUNCTION('MONTH', e.DueDate) = :month AND FUNCTION('YEAR', e.DueDate) = :year")
    List<Echeance> findEcheancesWithDueDateInCurrentMonth(@Param("month") int month, @Param("year") int year);

}
