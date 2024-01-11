package com.example.academia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.academia.models.Materia;
import com.example.academia.repositories.MateriaRepository;

@Service
public class MateriaService {
    
    private final MateriaRepository materiaRepository;

    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    public List<Materia> getAllMaterias() {
        return this.materiaRepository.findAll();
    }

    public Materia postMateria( Materia materia ){
        return this.materiaRepository.save(materia);
    }

    public boolean existsMateriaByName( String name ){
        return this.materiaRepository.findFirstByName( name ) != null;
    }
}