package com.example.academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.academia.models.Aula;

public interface AulaRepository extends JpaRepository<Aula, String> {
    
    
}
