package com.fullstack.duocflix.service;

import org.springframework.stereotype.Service;

import com.fullstack.duocflix.dto.MovieDto;
import com.fullstack.duocflix.model.Movie;

@Service
public class DtoService {

    public MovieDto toMovieDto(Movie movie) {
        return new MovieDto(movie.getId(), movie.getTitle(), movie.getYear());
    }

}
