package com.example.academia.web.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.academia.models.Materia;
import com.example.academia.service.MateriaService;
import com.example.academia.utils.GenerateResponse;
import com.example.academia.utils.MateriaValidations;

@RestController
@RequestMapping("/api-academia/v1/materias")
public class MateriaController {
    
    private final MateriaService materiaService;

    @Autowired
    private MateriaValidations materiaValidations;
    
    @Autowired
    private GenerateResponse generateResponse;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    
    @GetMapping()
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getAllActiveMaterias() {
        List<Materia> materias = materiaService.getAllActiveMaterias();
        return generateResponse.getResponse(materias);
    }


    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getMateriaById( @PathVariable String id ) {
        List<String> materiaErrors = materiaValidations.isValidMateriaId(id);
        Materia materia = materiaService.getMateriaById(id);

        return generateResponse.getResponse( materia, materiaErrors );
    }


    @PostMapping()
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> postMateria( @RequestBody Materia materia ) {

        Materia materiaAdd = null;
        List<String> materiaErrors = materiaValidations.isValidMateria(materia);

        if( materiaErrors.isEmpty() ) {
            materia.setIdMateria( UUID.randomUUID().toString() );
            materia.setActive(true);
            materiaAdd = materiaService.postMateria(materia);
        }
        
        return generateResponse.getResponse( materiaAdd, materiaErrors );
    }

    //delete logico
    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> deleteMateriaById( @PathVariable String id ) {
        Materia materiaDeleted = null;
        List<String> materiaErrors = materiaValidations.isValidMateriaId(id);
        
        if( materiaErrors.isEmpty() ) {
            materiaDeleted = materiaService.deleteMateriaById( id );
        }

        return generateResponse.getResponse( materiaDeleted, materiaErrors );
    }
}




    