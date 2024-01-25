package com.example.academia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.academia.models.Aula;
import com.example.academia.models.AulaResponse;
import com.example.academia.repositories.AulaRepository;
import com.example.academia.utils.GenerateResponse;

@Service
public class AulaService {
    
    private final AulaRepository aulaRepository;

    @Autowired
    private GenerateResponse generateResponse;

    public AulaService(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }

    public List<Aula> getAllAulas(){
        return this.aulaRepository.findAll();
    }

    public List<AulaResponse> getAllActiveAulas(){
        List<Aula> aulas = this.aulaRepository.findAllByActiveTrue();
        return generateResponse.getAulasResponse(aulas);
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
