package com.example.academia.service;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.academia.models.Profesor;
import com.example.academia.repositories.ProfesorRepository;


@Service
public class ProfesorService {
    
    private final ProfesorRepository profesorRepository;
    
    // @Autowired
    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public List<Profesor> getAllProfesores() {
        return this.profesorRepository.findAll();
    }

    public Profesor getProfesorById( String id ){
        return this.profesorRepository.findById( id ).orElse(null);
    }

    public Profesor postProfesor( Profesor profesor ){
        return this.profesorRepository.save(profesor);
    }

    public boolean existsProfesorByCi( String ci ){
        return this.profesorRepository.findAllByCi( ci ) != null;
    }
}
