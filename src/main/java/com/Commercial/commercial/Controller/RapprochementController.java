package com.Commercial.commercial.Controller;


import com.Commercial.commercial.DAO.Echeance;
import com.Commercial.commercial.DAO.Rapprochement;
import com.Commercial.commercial.Service.RapprochementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path="/Rapprochement")
@RequiredArgsConstructor
public class RapprochementController {
    public  final RapprochementService rappSer;
    @GetMapping(path="/getRapprochement")
    public ResponseEntity<Rapprochement> getRapprochement(){
        Rapprochement response=rappSer.calculateMonthlyEstimatedRevenu();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
