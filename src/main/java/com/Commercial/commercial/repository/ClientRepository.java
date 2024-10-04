package com.Commercial.commercial.repository;

import com.Commercial.commercial.DAO.client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<client, Integer> {
    Optional<client> findByEmail(String Email);
}
