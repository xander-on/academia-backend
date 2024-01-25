package com.example.academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.academia.models.Profesor;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, String> {
    
    Profesor findAllByCi( String ci );

    List<Profesor> findAllByActiveTrue();

    Profesor findFirstByIdAndActiveTrue(String id);
}
