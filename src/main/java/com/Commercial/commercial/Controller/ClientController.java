package com.Commercial.commercial.Controller;

import com.Commercial.commercial.Constants.CONSTANTS;
import com.Commercial.commercial.DAO.client;
import com.Commercial.commercial.Service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/client")
@RequiredArgsConstructor
public class ClientController {

    public final ClientService clientService;

    @PostMapping(path = "/AddClient")
    public ResponseEntity<String> addClient(@Valid @RequestBody client c) throws Exception {
      try{  clientService.addClient(c);
        return new ResponseEntity<>(CONSTANTS.CLIENT_CREATED_SUCESSFULLY, HttpStatus.OK); // Return the created client with a 201 status
    }catch (Exception e){
          return new ResponseEntity<>(CONSTANTS.SOMETHING_WENT_WRONG+e, HttpStatus.OK); // Return the created client with a 201 status

      }
    }

    @GetMapping(path = "/getAllClients")
    public List<client> addClient() throws Exception {
        try{
            return clientService.getAllClients();

        }catch (Exception e){
            return (List<client>) new ResponseEntity<>(CONSTANTS.SOMETHING_WENT_WRONG, HttpStatus.OK); // Return the created client with a 201 status

        }
    }

    @DeleteMapping(path = "/deleteClient/{id}")
    public String deletClient(@PathVariable Integer id) throws Exception {
        try{
            return clientService.deleteClient(id);

        }catch (Exception e){
            return String.valueOf(new ResponseEntity<>(CONSTANTS.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR)); // Return the created client with a 201 status

        }
    }

    @PutMapping(path = "/updateClient/{id}")
    public client updateClient(@PathVariable Integer id, @RequestBody client c) throws Exception {

            return clientService.updateClient(id,c);


    }

    @GetMapping(path = "/getClientByMail/{email}")
    public client getClientById(@PathVariable String email)  {

        return clientService.getClientById(email);


    }
}
