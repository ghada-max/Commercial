package com.Commercial.commercial.repository;

import com.Commercial.commercial.DAO.devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface devisRepository extends JpaRepository<devis,Integer> {
}
