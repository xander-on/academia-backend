package com.example.academia.web.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.academia.models.Aula;
import com.example.academia.models.AulaResponse;
import com.example.academia.service.AulaService;
import com.example.academia.utils.AulaValidations;
import com.example.academia.utils.GenerateResponse;

@RestController
@RequestMapping("/api-academia/v1/aulas")
public class AulaController {
    
    private final AulaService aulaService;

    @Autowired
    private AulaValidations aulaValidations;

    @Autowired
    private GenerateResponse generateResponse;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @GetMapping()
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getAllActiveAulas() {
        List<AulaResponse> aulas = aulaService.getAllActiveAulas();
        return generateResponse.getResponse(aulas);
    }


    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getAulaById( @PathVariable String id ) {

        Aula aula = null;
        List<String> errors = aulaValidations.isValidId(id);
        
        if( errors.isEmpty() ) 
            aula = aulaService.getAulaById(id);

        return generateResponse.getResponse(aula, errors);
    }
    

    @PostMapping()
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> postAula( @RequestBody Aula aula ) {

        Aula aulaAdd = null;
        List<String> aulaErrors = aulaValidations.isValidAula(aula);

        if( aulaErrors.isEmpty() ) {
            aula.setId( UUID.randomUUID().toString() );
            aula.setActive(true);
            aulaAdd = aulaService.postAula(aula);
        }
        
        return generateResponse.getResponse(aulaAdd, aulaErrors);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> deleteAula( @PathVariable String id ) {

        Aula aulaDeleted = null;
        List<String> errors = aulaValidations.isValidId(id);

        if( errors.isEmpty() ) 
            aulaDeleted = aulaService.deleteAulaById(id);

        return generateResponse.getResponse(aulaDeleted, errors);
    }
}
