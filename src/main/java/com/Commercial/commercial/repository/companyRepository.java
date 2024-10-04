package com.Commercial.commercial.repository;

import com.Commercial.commercial.DAO.company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface companyRepository extends JpaRepository<company,Integer> {
}
