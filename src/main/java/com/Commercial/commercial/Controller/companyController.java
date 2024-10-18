package com.Commercial.commercial.Controller;


import com.Commercial.commercial.DAO.company;
import com.Commercial.commercial.Service.companyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/company")
public class companyController {

    public final companyService companyService;

    @PostMapping(path="/createCompany")
    public ResponseEntity<company> createCompany(@RequestBody company comp) throws Exception {try
        {
            company createdCompany = companyService.createCompany(comp);
            return new ResponseEntity<>(createdCompany, HttpStatus.OK);
        }
        catch(Exception e){throw new Exception(e);}
    }

    @PutMapping(path="/updateCompany")
    public ResponseEntity<company> updateCompany(@RequestBody company comp) throws Exception {try
    {
        company updatedCompany = companyService.updateCompany(comp);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }
    catch(Exception e){throw new Exception(e);}
    }

    @GetMapping(path="/getCompany")
    public ResponseEntity<Optional<company>> getCompany()
    {
      Optional<company> comp=companyService.getCompany();
        return new ResponseEntity<>(comp, HttpStatus.OK);


    }
}
