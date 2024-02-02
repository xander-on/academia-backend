package com.example.academia.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import com.example.academia.models.ApiResponse;
import com.example.academia.models.Aula;
import com.example.academia.models.AulaResponse;


@Component
public class GenerateResponse{

    @Value("${base_url}")  
    private String base_url;

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

         response.setOk(true);
         response.setTotal(resultsList.size());
         response.setResults(resultsList);
         response.setErrors(Collections.emptyList());

        return ResponseEntity.ok(response);
    }


    public List<AulaResponse> getAulasResponse(List<Aula> aulas){
        List<AulaResponse> aulasResponse = new ArrayList<>();
        
        for (Aula aula : aulas) {
            AulaResponse aulaResponse = createAulaResponse(aula);
            aulasResponse.add(aulaResponse);
        }
        return aulasResponse;
    }


    public AulaResponse getAulaResponse(Aula aula){
        return createAulaResponse(aula);
    }


    private AulaResponse createAulaResponse(Aula aula){
        AulaResponse aulaResponse = new AulaResponse();
        aulaResponse.setId(aula.getId());
        aulaResponse.setCodigo(aula.getCodigo());
        aulaResponse.setDate(aula.getDate());
        aulaResponse.setTime(aula.getTime());
        aulaResponse.setTheme(aula.getTheme());
        aulaResponse.setActive(aula.isActive());
        aulaResponse.setProfesor( base_url + "/profesores/" + aula.getProfesor().getId());
        aulaResponse.setMateria(base_url + "/materias/" + aula.getMateria().getId());

        return aulaResponse;
    }
    
}