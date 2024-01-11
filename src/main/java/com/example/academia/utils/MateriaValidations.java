package com.example.academia.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.academia.models.Materia;
import com.example.academia.service.MateriaService;

@Component
public class MateriaValidations {

    @Autowired
    private MateriaService materiaService;

    public List<String> isValidMateria(Materia materia) {

        List<String> materiaErrors = new ArrayList<>();

        if( materia.getName() == null){
            materiaErrors.add("La materia debe ser registrado con un nombre (name)");
        }

        if( materiaService.existsMateriaByName(materia.getName()) ){
            materiaErrors.add("Ya existe una materia con ese nombre registrado");
        }

        return materiaErrors;
    }
}
