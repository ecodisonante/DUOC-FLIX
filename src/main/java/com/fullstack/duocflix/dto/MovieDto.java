package com.fullstack.duocflix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieDto {
    private long id;
    private String title;
    private int year;
}
