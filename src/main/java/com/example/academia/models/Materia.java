package com.example.academia.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "materias")
public class Materia {
    @Id
    @Column( name="id", nullable = false, unique = true )
    private String id;

    @Column( nullable = false, length = 60, unique = true )
    private String name;

    @Column( nullable = false, columnDefinition = "TINYINT" )
    private boolean active;

}
