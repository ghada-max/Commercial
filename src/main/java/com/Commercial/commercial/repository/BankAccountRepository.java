package com.Commercial.commercial.repository;

import com.Commercial.commercial.DAO.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,Integer> {
}
