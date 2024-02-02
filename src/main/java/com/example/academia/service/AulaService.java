package com.example.academia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.academia.models.Aula;
import com.example.academia.repositories.AulaRepository;

@Service
public class AulaService {
    
    private final AulaRepository aulaRepository;

    public AulaService(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }

    public List<Aula> getAllAulas(){
        return this.aulaRepository.findAll();
    }

    public List<Aula> getAllActiveAulas(){
        return this.aulaRepository.findAllByActiveTrue();
    }

    public Aula getAulaById( String id ){
        return this.aulaRepository.findById(id).orElse(null);
    }

    public Aula postAula( Aula aula ){
        return this.aulaRepository.save( aula );
    }

    public Aula deleteAulaById( String id ){
        Aula aula = getAulaById(id);
        aula.setActive(false);
        return this.aulaRepository.save( aula );
    }
}
