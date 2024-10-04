package com.Commercial.commercial.repository;

import com.Commercial.commercial.DAO.supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface supplierRepository extends JpaRepository<supplier,Integer> {
    Optional<supplier> findByEmail(String email);
}
