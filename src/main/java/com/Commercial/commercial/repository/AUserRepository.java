package com.Commercial.commercial.repository;

import com.Commercial.commercial.DAO.AUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AUserRepository extends JpaRepository<AUser,Integer> {
}
