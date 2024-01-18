package com.example.academia.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.academia.models.Aula;
import com.example.academia.service.AulaService;

@Component
public class AulaValidations {

    @Autowired
    private AulaService aulaService;

    public List<String> isValidAula( Aula aula ) {

        List<String> aulaErrors = new ArrayList<>();

        if( aula.getCodigo() == null || aula.getCodigo().isEmpty() ){
            aulaErrors.add("El aula debe ser registrado con un codigo");
        }

        if( aula.getDate() == null || aula.getDate().toString().isEmpty() ){
            aulaErrors.add("La fecha es requerida");
        }

        if( aula.getTime() == null || aula.getTime().toString().isEmpty() ){
            aulaErrors.add("La hora es requerida");
        }

        //todo si la materia o el profesor existen

        return aulaErrors;
    }
}
