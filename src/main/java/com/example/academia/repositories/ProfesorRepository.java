package com.example.academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.academia.models.Profesor;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, String> {
    
    Profesor findAllByCi( String ci );
}
