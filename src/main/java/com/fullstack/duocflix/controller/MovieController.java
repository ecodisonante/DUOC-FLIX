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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    IMovieService _service;
    @Autowired
    DtoService _dto;

    @GetMapping
    public ResponseEntity<Object> getMovieList() {
        try {
            List<Movie> movies = _service.getAllMovies();

            if (movies.isEmpty())
                return ResponseEntity.ok(new ExceptionResponse(
                        HttpStatus.OK.value(), "Lo sentimos, no hay peliculas disponibles"));

            return ResponseEntity.ok(movies.stream()
                    .map(movie -> _dto.toMovieDto(movie))
                    .toList());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ExceptionResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMovie(@PathVariable Long id) {
        try {
            Optional<Movie> movie = _service.getMovieById(id);

            if (!movie.isPresent())
                return ResponseEntity.badRequest().body(new ExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(), "No existe la pelicula con el id " + id));

            return ResponseEntity.ok(movie.get());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ExceptionResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Object> createMovie(@RequestBody Movie movie) {
        try {
            return ResponseEntity.ok(_service.createMovie(movie));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ExceptionResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putMethodName(@PathVariable Long id, @RequestBody Movie movie) {
        try {
            return ResponseEntity.ok(_service.updateMovie(id, movie));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ExceptionResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable Long id) {
        try {
            if (_service.deleteMovie(id)) {
                return ResponseEntity.ok(new ExceptionResponse(HttpStatus.OK.value(), "Película eliminada."));
            } else {
                return ResponseEntity.badRequest().body(new ExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(), "No existe la pelicula con el id " + id));
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ExceptionResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

}
