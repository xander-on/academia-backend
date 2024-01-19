package com.example.academia.utils;
import com.example.academia.models.Profesor;
import com.example.academia.service.ProfesorService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfesorValidations {

    @Autowired
    private ProfesorService profesorService;
    
    public List<String> isValidProfesor(Profesor profesor) {

        List<String> profesorErrors = new ArrayList<>();

        if( profesor.getCi() == null){
            profesorErrors.add("El profesor debe ser registrado con un CI");
        }

        if( profesor.getName() == null || profesor.getName().isEmpty()){
            profesorErrors.add("El profesor debe ser registrado con un Nombre");
        }

        if( profesor.getEmail() == null){
            profesorErrors.add("El profesor debe ser registrado con un Email");
        }

        if( !isValidCI(profesor.getCi()) ){
            profesorErrors.add("El CI debe ser de 10 digitos y debe ser solo numeros");
        }

        if( profesorService.existsProfesorByCi(profesor.getCi()) ){
            profesorErrors.add("El numero de CI " + profesor.getCi() + " ya esta registrado");
        }

        // todo is valid email y que sea unico

        return profesorErrors;
    }

    public boolean isValidCI(String ci) {
        return ci != null && ci.matches("\\d{10}");
    }


    public List<String> isValidId( String id ){
        List<String> materiaErrors = new ArrayList<>();

        if( !existsProfesorById( id ) )
            materiaErrors.add("No existe una profesor con ese id");

        return materiaErrors;
    }


    public boolean existsProfesorById( String id ){
        return this.profesorService.getProfesorById( id ) != null;
    }
}
