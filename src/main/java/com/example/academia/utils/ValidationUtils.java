package com.example.academia.utils;
import com.example.academia.models.Profesor;
import com.example.academia.service.ProfesorService;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtils {

    @Autowired
    private ProfesorService profesorService;
    
    public List<String> isValidProfesor(Profesor profesor) {

        List<String> profesorErrors = new ArrayList<>();
        

        if( profesor.getCi() == null){
            profesorErrors.add("El profesor debe ser registrado con un CI");
        }

        if( profesor.getName() == null){
            profesorErrors.add("El profesor debe ser registrado con un Nombre");
        }

        if( profesor.getEmail() == null){
            profesorErrors.add("El profesor debe ser registrado con un Email");
        }

        if( !isValidCI(profesor.getCi()) ){
            profesorErrors.add("El CI debe ser de 10 digitos y debe ser solo numeros");
        }

        if( profesorService.existsProfesorByCi(profesor.getCi()) ){
            profesorErrors.add("El CI ya esta registrado");
        }

        return profesorErrors;
    }

    public boolean isValidCI(String ci) {
        return ci != null && ci.matches("\\d{10}");
    }
}
