package com.Commercial.commercial.Service;

import com.Commercial.commercial.Constants.CONSTANTS;
import com.Commercial.commercial.DAO.BankAccount;
import com.Commercial.commercial.repository.BankAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {
    @Autowired
     BankAccountRepository bankAccountRepo;

    public String deleteBankAccount(Integer id) {
        return bankAccountRepo.findById(id).map(bankAccount -> {
            bankAccountRepo.deleteById(id);
            return "Bank account deleted successfully"; // Corrected the return message
        }).orElseThrow(() -> new EntityNotFoundException("Bank account with id: " + id + " not found"));
    }

    public String addBankAccount(BankAccount c) {


        bankAccountRepo.save(c);
        return "bankAccount created successfully";
    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepo.findAll();    }

    public BankAccount updateBankAccount(Integer id, BankAccount c) {
            return bankAccountRepo.findById(id).map(updatedBankAccount -> {
                updatedBankAccount.setAccountNumber(c.getAccountNumber());
                updatedBankAccount.setBalance(c.getBalance());
                updatedBankAccount.setBankName(c.getBankName());
                bankAccountRepo.save(updatedBankAccount);
                return updatedBankAccount;
            }).orElseThrow(() -> new EntityNotFoundException("Bank acconut not found while updating"));

    }

    public BankAccount getBankAccountById(Integer id) {
        return bankAccountRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Bank acconut not found while updating"));

    }
}
