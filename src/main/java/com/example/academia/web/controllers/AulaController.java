package com.example.academia.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.academia.models.Aula;
import com.example.academia.service.AulaService;
import com.example.academia.utils.AulaValidations;
import com.example.academia.utils.MateriaValidations;

@RestController
@RequestMapping("/api-academia/v1/aulas")
public class AulaController {
    
    private final AulaService aulaService;

    @Autowired
    private AulaValidations aulaValidations;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @GetMapping()
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getAllActiveMaterias() {
        List<Aula> aulas = aulaService.getAllAulas();
        HashMap<String, Object> response = new HashMap<>();

        response.put("ok", true);
        response.put("results", aulas);
        response.put("total", aulas.size());
        
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getAulaById( @PathVariable String id ) {
        Aula aula = aulaService.getAulaById(id);
        HashMap<String, Object> response = new HashMap<>();
        
        if( aula == null ) {
            response.put("ok", true);
            response.put("results", new Object[0]);
            return ResponseEntity.ok().body(response);
        }

        List<Aula> resultsList = new ArrayList<>();
        resultsList.add(aula);

        response.put("ok", true);
        response.put("results", resultsList);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> postAula( @RequestBody Aula aula ) {
        List<String> aulaErrors = aulaValidations.isValidAula(aula);

        if( !aulaErrors.isEmpty() ) {
            Map<String, Object> responseErrors = new HashMap<>();
            responseErrors.put("errors", aulaErrors);
            return ResponseEntity.badRequest().body(responseErrors);
        }

        aula.setIdAula( UUID.randomUUID().toString() );
        aula.setActive(true);
        return ResponseEntity.ok(aulaService.postAula(aula));
        
    }
}
