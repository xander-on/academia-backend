package com.example.academia.web.controllers;

import com.example.academia.models.Profesor;
import com.example.academia.service.ProfesorService;
import com.example.academia.utils.GenerateResponse;
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

    @Autowired
    private GenerateResponse generateResponse;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping()
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getAllActiveProfesores() {
        List<Profesor> profesores = profesorService.getAllActiveProfesores();
        return generateResponse.getResponse(profesores);
    }


    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getProfesorById( @PathVariable String id ) {

        List<String> profesorErrors = profesorValidations.isValidId(id);
        Profesor profesor = null;

        if( profesorErrors.isEmpty() )
            profesor = profesorService.getProfesorById(id);

        return generateResponse.getResponse(profesor, profesorErrors);
    }


    @PostMapping()
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> postProfesor( @RequestBody Profesor profesor ) {

        Profesor profesorAdd = null;
        List<String> profesorErrors = profesorValidations.isValidProfesor(profesor);

        if( profesorErrors.isEmpty() ) {
            profesor.setIdProfesor( UUID.randomUUID().toString() );
            profesor.setActive(true);
            profesor.setPhoto("https://api.multiavatar.com/"+ profesor.getIdProfesor() +".png");
            profesorAdd = profesorService.postProfesor(profesor);
        }

        return generateResponse.getResponse(profesorAdd, profesorErrors);
    }


    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> deleteProfesor( @PathVariable String id ) {

        Profesor materiaDeleted = null;
        List<String> profesorErrors = profesorValidations.isValidId(id);

        if( profesorErrors.isEmpty() )
            materiaDeleted = profesorService.deleteProfesorById(id);

        return generateResponse.getResponse(materiaDeleted, profesorErrors);
    }
}
