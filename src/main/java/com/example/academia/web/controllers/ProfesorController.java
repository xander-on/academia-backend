package com.example.academia.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.academia.models.Profesor;
import com.example.academia.service.ProfesorService;
import com.example.academia.utils.ValidationUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api-academia/v1/profesores")
public class ProfesorController {
    
    private final ProfesorService profesorService;
    
    @Autowired
    private ValidationUtils validationUtils;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping()
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Profesor>> getAllProfesores() {
        return ResponseEntity.ok(profesorService.getAllProfesores());
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> postProfesor( @RequestBody Profesor profesor ) {
        
        List<String> profesorErrors = validationUtils.isValidProfesor(profesor);

        if( !profesorErrors.isEmpty() ) {
            Map<String, Object> responseErrors = new HashMap<>();
            responseErrors.put("errors", profesorErrors);
            return ResponseEntity.badRequest()
                .body(responseErrors);
        }

        profesor.setId( UUID.randomUUID().toString() );
        profesor.setActive(true);
        return ResponseEntity.ok(profesorService.postProfesor(profesor));
    }
    
}
