package com.example.academia.models;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table( name="aulas" )
public class Aula {
    @Id
    @Column( name="id", nullable = false, unique = true )
    private String id;

    @Column( nullable = false, length = 10)
    private String codigo;

    @Column( nullable = false )
    private LocalDate date;

    @Column( nullable = false )
    private LocalTime time;

    @Column( nullable = false, length = 60 )
    private String theme;

    @Column( nullable = false, columnDefinition = "TINYINT" )
    private boolean active;

    @ManyToOne
    @JoinColumn(
        name = "id_materia",
        referencedColumnName = "id"
    ) private Materia materia;

    @ManyToOne
    @JoinColumn(
        name = "id_profesor",
        referencedColumnName = "id"
    ) private Profesor profesor;
}
