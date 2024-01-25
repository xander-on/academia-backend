package com.example.academia.models;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AulaResponse {
    private String id;
    private String codigo;
    private LocalDate date;
    private LocalTime time;
    private String theme;
    private boolean active;
    private String profesor;
    private String materia;

}