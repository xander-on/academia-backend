package com.example.academia.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.academia.models.Profesor;
import com.example.academia.service.ProfesorService;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api-academia/v1/profesores")
public class ProfesorController {
    
    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping()
    public ResponseEntity<List<Profesor>> getAllProfesores() {
        return ResponseEntity.ok(profesorService.getAllProfesores());
    }

    @PostMapping()
    public ResponseEntity<Profesor> postProfesor( @RequestBody Profesor profesor ) {
        return ResponseEntity.ok(profesorService.postProfesor(profesor));
    }
    
}
