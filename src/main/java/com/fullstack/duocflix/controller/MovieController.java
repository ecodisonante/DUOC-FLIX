package com.fullstack.duocflix.controller;

import java.util.List;
import java.util.Optional;

import com.fullstack.duocflix.dto.ExceptionResponse;
import com.fullstack.duocflix.model.Movie;
import com.fullstack.duocflix.service.DtoService;
import com.fullstack.duocflix.service.IMovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    IMovieService _service;
    @Autowired
    DtoService _dto;

    @GetMapping
    public ResponseEntity<Object> getMovieList() {
        List<Movie> movies = _service.getAllMovies();

        if (movies.isEmpty())
            return ResponseEntity.ok(new ExceptionResponse(
                    HttpStatus.OK.value(), "Aun no hay peliculas disponibles"));

        return ResponseEntity.ok(movies.stream()
                .map(movie -> _dto.toMovieDto(movie))
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMovie(@PathVariable Long id) {
        Optional<Movie> movie = _service.getMovieById(id);

        if (!movie.isPresent())
            return ResponseEntity.badRequest().body(new ExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), "No existe la pelicula con el id " + id));

        return ResponseEntity.ok(movie.get());
    }

}
