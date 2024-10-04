package com.Commercial.commercial.repository;

import com.Commercial.commercial.DAO.category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<category,Integer> {
}
