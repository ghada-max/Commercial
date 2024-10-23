package com.Commercial.commercial.repository;

import com.Commercial.commercial.DAO.Rapprochement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RapprochementRepository extends JpaRepository<Rapprochement,Integer> {
}
