package com.Commercial.commercial.repository;

import com.Commercial.commercial.DAO.Rapprochement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RapprochementRepository extends JpaRepository<Rapprochement,Integer> {
    @Query("SELECT r FROM Rapprochement r WHERE FUNCTION('MONTH', r.date) = :month AND FUNCTION('YEAR', r.date) = :year")
    Optional<Rapprochement> findRapprochementWithDateInCurrentMonth(@Param("month") int month, @Param("year") int year);

}
