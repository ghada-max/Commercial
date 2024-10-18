package com.Commercial.commercial.Controller;

import com.Commercial.commercial.Constants.CONSTANTS;
import com.Commercial.commercial.DAO.BankAccount;
import com.Commercial.commercial.Service.BankAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.List;

@RestController
@RequestMapping(path="/BankAccount")
@RequiredArgsConstructor
public class BankAccountController {

    public final BankAccountService BankAccountServ;

    @PostMapping(path = "/addBankAccount")
    public ResponseEntity<String> addBankAccount(@Valid @RequestBody BankAccount c) throws Exception {
        try{  BankAccountServ.addBankAccount(c); // Store the returned client from the service
            return new ResponseEntity<>("bank account created successfully", HttpStatus.OK); // Return the created client with a 201 status
        }catch (Exception e){
            return new ResponseEntity<>(CONSTANTS.SOMETHING_WENT_WRONG+e, HttpStatus.OK); // Return the created client with a 201 status

        }
    }

    @GetMapping(path = "/getAllBankAccounts")
    public List<BankAccount> getAllBankAccounts() throws Exception {
        try{
            return BankAccountServ.getAllBankAccounts();

        }catch (Exception e){
            return (List<BankAccount>) new ResponseEntity<>(CONSTANTS.SOMETHING_WENT_WRONG, HttpStatus.OK); // Return the created client with a 201 status

        }
    }

    @DeleteMapping(path = "/deleteBankAccount/{id}")
    public String deleteBankAccount(@PathVariable Integer id) throws Exception {
        try{
            return BankAccountServ.deleteBankAccount(id);

        }catch (Exception e){
            return String.valueOf(new ResponseEntity<>(CONSTANTS.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR)); // Return the created client with a 201 status

        }
    }

    @PutMapping(path = "/updateBankAccount/{id}")
    public BankAccount updateBankAccount(@PathVariable Integer id, @RequestBody BankAccount c) throws Exception {

        return BankAccountServ.updateBankAccount(id,c);


    }

    @GetMapping(path = "/getBankAccountById/{id}")
    public BankAccount getBankAccountById(@PathVariable Integer id)  {

        return BankAccountServ.getBankAccountById(id);



}

}
