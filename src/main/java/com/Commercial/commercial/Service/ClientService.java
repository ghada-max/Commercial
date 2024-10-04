package com.Commercial.commercial.Service;

import com.Commercial.commercial.Constants.CONSTANTS;
import com.Commercial.commercial.DAO.client;
import com.Commercial.commercial.DAO.company;
import com.Commercial.commercial.repository.ClientRepository;
import com.Commercial.commercial.repository.companyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepo;

    @Autowired
    companyRepository companyRep;

    public client addClient(client c) throws Exception {
        if(emailexist(c.getEmail())){
            throw new Exception(CONSTANTS.EMAIL_EXISTING);
        }

        clientRepo.save(c);
        return c;
    }

    public boolean emailexist(String email){
       return clientRepo.findByEmail(email).isPresent();
    }

    public List<client> getAllClients() {
        return clientRepo.findAll();
    }

    public String deleteClient(Integer id) {

        if (userExist(id)){
        clientRepo.deleteById(id);
        return "Client deleted successfully";}
        return "client not found";


    }



    public boolean userExist(Integer id){
        return clientRepo.findById(id).isPresent();
    }

    public client updateClient(Integer id, client c) throws Exception {

     return clientRepo.findById(id).map(existingClient->{
          existingClient.setName(c.getName());
          existingClient.setAddress(c.getAddress());
          existingClient.setEmail(c.getEmail());
          existingClient.setPhone(c.getPhone());
         return  clientRepo.save(existingClient);
              })
             .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + id));
    }

    public client getClientById(Integer id) {
       return clientRepo.findById(id).orElseThrow(()->new EntityNotFoundException("client with id "+id+"not found"));
    }
}
