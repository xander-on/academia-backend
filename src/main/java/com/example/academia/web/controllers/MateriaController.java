package com.example.academia.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Materia>> getAllMaterias() {
        return ResponseEntity.ok(materiaService.getAllMaterias());
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
}
