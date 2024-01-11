package com.example.academia.models;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class Aula {
    @Id
    private String id;

    @Column( nullable = false, length = 60, unique = true )
    private String codigo;

    @Column( nullable = false )
    private LocalDate date;

    @Column( nullable = false )
    private LocalTime time;

    @Column( nullable = false, length = 60 )
    private String theme;

    @Column( nullable = false, columnDefinition = "TINYINT" )
    private boolean active;
}
