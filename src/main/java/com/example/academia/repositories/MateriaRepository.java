package com.example.academia.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.academia.models.Materia;

public interface MateriaRepository extends JpaRepository<Materia, String> {
    
    Materia findFirstByName( String name );

    List<Materia> findAllByActiveTrue();
}
