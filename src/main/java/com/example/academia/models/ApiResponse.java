package com.example.academia.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ApiResponse {
    private boolean ok;
    private int total;
    private List<?> errors;
    private List<?> results;
}
