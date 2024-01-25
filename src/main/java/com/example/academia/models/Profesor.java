package com.example.academia.models;

import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="profesores")
public class Profesor {
    @Id
    @Column( name="id", nullable = false, unique = true )
    private String id;

    @Size(min = 10, max = 10)
    @Column( nullable = false, length = 10, unique = true )
    private String ci;

    @Column( nullable = false, length = 60 )
    private String name;

    @Column( nullable = false, length = 60 )
    private String email;

    @Column( columnDefinition = "TINYINT" )
    private boolean active;

    @Column
    private String photo;
}
