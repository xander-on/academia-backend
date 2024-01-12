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

        if( aula.getCodigo() == null){
            aulaErrors.add("El aula debe ser registrado con un codigo");
        }

        return aulaErrors;
    }
}
