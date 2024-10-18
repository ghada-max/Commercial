package com.Commercial.commercial.repository;

import com.Commercial.commercial.DAO.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface commissionRepository extends JpaRepository<Commission,Integer> {
}
