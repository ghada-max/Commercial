package com.Commercial.commercial.repository;

import com.Commercial.commercial.DAO.AUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AUserRepository extends JpaRepository<AUser,Integer> {
    Optional<AUser> findByEmail(String email);
}
