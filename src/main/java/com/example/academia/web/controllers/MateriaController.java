package com.example.academia.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.academia.models.Materia;
import com.example.academia.service.MateriaService;
import com.example.academia.utils.MateriaValidations;

@RestController
@RequestMapping("/api-academia/v1/materias")
public class MateriaController {
    
    private final MateriaService materiaService;

    @Autowired
    private MateriaValidations materiaValidations;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping()
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getAllActiveMaterias() {
        List<Materia> materias = materiaService.getAllActiveMaterias();
        HashMap<String, Object> response = new HashMap<>();

        response.put("ok", true);
        response.put("results", materias);
        response.put("total", materias.size());
        
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getProfesorById( @PathVariable String id ) {
        Materia materia = materiaService.getMateriaById(id);
        HashMap<String, Object> response = new HashMap<>();
        
        if( materia == null ) {
            response.put("ok", true);
            response.put("results", new Object[0]);
            return ResponseEntity.ok(response);
        }

        List<Materia> resultsList = new ArrayList<>();
        resultsList.add(materia);

        response.put("ok", true);
        response.put("results", resultsList);
        return ResponseEntity.ok(response);
    }


    @PostMapping()
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> postMateria( @RequestBody Materia materia ) {

        List<String> materiaErrors = materiaValidations.isValidMateria(materia);

        if( !materiaErrors.isEmpty() ) {
            Map<String, Object> responseErrors = new HashMap<>();
            responseErrors.put("errors", materiaErrors);
            return ResponseEntity.badRequest()
                .body(responseErrors);
        }

        materia.setId( UUID.randomUUID().toString() );
        materia.setActive(true);
        return ResponseEntity.ok(materiaService.postMateria(materia));
    }


    //delete logico
    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> deleteMateria( @PathVariable String id ) {

        HashMap<String, Object> response = new HashMap<>();
        Materia materiaSearched = materiaService.getMateriaById(id);

        if( materiaSearched == null ) {
            response.put("ok", false);
            response.put("results",  new Object[0]);
            return ResponseEntity.ok(response);
        }

        Materia materiaDeleted = materiaService.deleteMateria(materiaSearched);

        List<Materia> resultsList = new ArrayList<>();
        resultsList.add(materiaDeleted);

        response.put("ok", true);
        response.put("results", resultsList);

        return ResponseEntity.ok(response);
    }
}
