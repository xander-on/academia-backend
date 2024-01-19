package com.example.academia.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.example.academia.models.ApiResponse;

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
    
}