package com.example.academia.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.academia.models.Profesor;
import com.example.academia.service.ProfesorService;
import com.example.academia.utils.ProfesorValidations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api-academia/v1/profesores")
public class ProfesorController {
    
    private final ProfesorService profesorService;

    @Autowired
    private ProfesorValidations profesorValidations;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping()
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getAllActiveProfesores() {
        List<Profesor> profesores = profesorService.getAllActiveProfesores();
        HashMap<String, Object> response = new HashMap<>();

        response.put("ok", true);
        response.put("results", profesores);
        response.put("total", profesores.size());

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getProfesorById( @PathVariable String id ) {
        Profesor profesor = profesorService.getProfesorById(id);
        HashMap<String, Object> response = new HashMap<>();
        
        if( profesor == null ) {
            response.put("ok", true);
            response.put("results", new Object[0]);
            return ResponseEntity.ok().body(response);
        }

        List<Profesor> resultsList = new ArrayList<>();
        resultsList.add(profesor);

        response.put("ok", true);
        response.put("results", resultsList);
        return ResponseEntity.ok(response);
    }


    @PostMapping()
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> postProfesor( @RequestBody Profesor profesor ) {

        List<String> profesorErrors = profesorValidations.isValidProfesor(profesor);
        HashMap<String, Object> response = new HashMap<>();

        if( !profesorErrors.isEmpty() ) {
            Map<String, Object> responseErrors = new HashMap<>();
            responseErrors.put("errors", profesorErrors);
            responseErrors.put("ok", false);
            return ResponseEntity.badRequest()
                .body(responseErrors);
        }

        
        profesor.setIdProfesor( UUID.randomUUID().toString() );
        profesor.setActive(true);
        Profesor profesorAdd = profesorService.postProfesor(profesor);

        List<Profesor> resultsList = new ArrayList<>();
        resultsList.add(profesorAdd);

        response.put("ok", true);
        response.put("results", resultsList );

        return ResponseEntity.ok(response);
    }


    //delete logico
    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> deleteProfesor( @PathVariable String id ) {

        HashMap<String, Object> response = new HashMap<>();
        Profesor profesorSearched = profesorService.getProfesorById(id);

        if( profesorSearched == null ) {
            response.put("ok", false);
            response.put("results",  new Object[0]);
            return ResponseEntity.ok(response);
        }

        Profesor materiaDeleted = profesorService.deleteProfesor(profesorSearched);

        List<Profesor> resultsList = new ArrayList<>();
        resultsList.add(materiaDeleted);

        response.put("ok", true);
        response.put("results", resultsList);

        return ResponseEntity.ok(response);
    }
    
}
