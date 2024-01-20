package com.example.academia.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.academia.models.Aula;
import com.example.academia.service.AulaService;
import com.example.academia.service.MateriaService;
import com.example.academia.service.ProfesorService;

@Component
public class AulaValidations {

    @Autowired
    private AulaService aulaService;

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private ProfesorService profesorService;

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

        String idMateria = aula.getMateria().getIdMateria();
        String idProfesor = aula.getProfesor().getIdProfesor();

        if( !isActiveMateria( idMateria ) ){
            aulaErrors.add(
                ( !existIdMateria( idMateria ) )
                    ? "No existe una materia con el Id: " + idMateria
                    : "No se encuentra activa la materia con el Id: " + idMateria
            );
        }

        if( !isActiveProfesor( idProfesor ) ){
            aulaErrors.add(
                ( !existsIdProfesor( idProfesor ) )
                    ? "No existe un profesor con el Id: " + idProfesor
                    : "El profesor con el Id: " + idProfesor + " no esta activo"
            );
        }

        // if( true ) aulaErrors.add("Error");
        return aulaErrors;
    }


    public List<String> isValidId( String id ){
        List<String> errors = new ArrayList<>();

        if( !existAulaById( id ) )
            errors.add("No existe un aula con el id:" + id);

        return errors;
    }


    private boolean existIdMateria( String id ){
        return materiaService.getMateriaById( id ) != null;
    }


    private boolean isActiveMateria( String id ){
        return materiaService.getMateriaActiveById( id ) != null;
    }


    private boolean existsIdProfesor( String id ){
        return profesorService.getProfesorById( id ) != null;
    }


    private boolean isActiveProfesor( String id ){
        return profesorService.getProfesorActiveById( id ) != null;
    }


    public boolean existAulaById( String id ){
        return aulaService.getAulaById( id ) != null;
    }
}
