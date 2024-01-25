package com.example.academia.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.example.academia.models.ApiResponse;
import com.example.academia.models.Aula;
import com.example.academia.models.AulaResponse;

@Component
public class GenerateResponse{

    public ResponseEntity<?> getResponse(Object results) {
        return getResponse(results, Collections.emptyList());
    }

    public ResponseEntity<?> getResponse( Object results, List<String> errors) {
        ApiResponse response = new ApiResponse(
            false,
            0, 
            errors,
            Collections.emptyList()
        );

        if( results == null ){ 
            return ResponseEntity.badRequest().body(response); 
        }

        List<?> resultsList;

        if (results instanceof List<?>) {
            resultsList = (List<?>) results;
        }else{
            resultsList = Arrays.asList(results);
        }

         // Modificar los campos según sea necesario
         response.setOk(true);
         response.setTotal(resultsList.size());
         response.setResults(resultsList);
         response.setErrors(Collections.emptyList());

        return ResponseEntity.ok(response);
    }


    public List<AulaResponse> getAulasResponse(List<Aula> aulas){
        List<AulaResponse> aulasResponse = new ArrayList<>();
        for (Aula aula : aulas) {
            AulaResponse aulaResponse = new AulaResponse();
            aulaResponse.setId(aula.getId());
            aulaResponse.setCodigo(aula.getCodigo());
            aulaResponse.setDate(aula.getDate());
            aulaResponse.setTime(aula.getTime());
            aulaResponse.setTheme(aula.getTheme());
            aulaResponse.setActive(aula.isActive());
            aulaResponse.setProfesor("http://localhost:8080/api-academia/v1/profesores/" + aula.getProfesor().getId());
            aulaResponse.setMateria("http://localhost:8080/api-academia/v1/materias/" + aula.getMateria().getId());
            aulasResponse.add(aulaResponse);
        }
        return aulasResponse;
    }
    
}