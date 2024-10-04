package com.Commercial.commercial.repository;

import com.Commercial.commercial.DAO.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepository extends JpaRepository<product,Integer> {
}
