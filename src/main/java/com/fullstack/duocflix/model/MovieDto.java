package com.fullstack.duocflix.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieDto {

    long id;
    String title;
    int year;

}
