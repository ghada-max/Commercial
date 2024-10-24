package com.Commercial.commercial.Controller;

import com.Commercial.commercial.Constants.CONSTANTS;
import com.Commercial.commercial.DAO.company;
import com.Commercial.commercial.DAO.devis;
import com.Commercial.commercial.DAO.devisDTO;
import com.Commercial.commercial.Service.devisService;
import com.Commercial.commercial.Service.pdfGeneratorService;

import com.Commercial.commercial.repository.devisRepository;
import com.Commercial.commercial.repository.companyRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/devis")
public class devisController {
    public final devisService devisServ;
    public final pdfGeneratorService pdfGeneratorS;
    public final devisRepository deviRepo;
    @Autowired
    companyRepository companyRepo;



    @PostMapping(path="/createDevis")
    public ResponseEntity<devisDTO> createDevis(@RequestBody devis devi ) throws Exception {
        try{
            devisDTO createdDEvis=devisServ.createDevis(devi);
            return new ResponseEntity<>(createdDEvis, HttpStatus.OK);
        }
        catch (Exception e){throw new Exception(e);}
    }




    @GetMapping(path="/getDeviById/{id}")
    public ResponseEntity<devisDTO> getDeviById(@PathVariable Integer id ) throws Exception {

        devisDTO dv=devisServ.getDeviById(id);
            return new ResponseEntity<>(dv, HttpStatus.OK);


    }



    @DeleteMapping(path="/deleteDeviById/{id}")
    public ResponseEntity<String> deleteDeviById(@PathVariable Integer id ) throws Exception {
        try {
            String dv = devisServ.deleteDeviById(id);
            if (dv == "devis with id " + id + " deleted successfully") {
                return new ResponseEntity<>(dv, HttpStatus.OK);
            } else
                return new ResponseEntity<>(dv, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            throw new Exception(e);
        }

    }

    @GetMapping(path="/getAllDevis")
    public List<devisDTO>getAllDevis( ) {
       return devisServ.getAllDevis();
}
  @GetMapping("/devis/{id}/pdf")
    public ResponseEntity<InputStreamResource> generatePdf(@PathVariable Integer id) throws MalformedURLException {
      //  devis devi=deviRepo.findById(id).orElseThrow(()->new EntityNotFoundException("devis not found"));
        devisDTO devDTO=devisServ.getDeviById(id);
      company comp = companyRepo.findAll().stream().findFirst()
              .orElseThrow(() -> new EntityNotFoundException("Company not found"));
      ByteArrayInputStream bis=pdfGeneratorS.generatePdf(devDTO,comp);
        HttpHeaders header=new HttpHeaders();
        header.add("Content-Disposition","Inline; filename=devis_"+ id +".pdf");
        return ResponseEntity.ok()
                .headers(header)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
  }
}
