package com.fullstack.duocflix.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;

import com.fullstack.duocflix.dto.MovieDto;
import com.fullstack.duocflix.model.Movie;
import com.fullstack.duocflix.service.DtoService;
import com.fullstack.duocflix.service.IMovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    IMovieService _service;
    @Autowired
    DtoService _dto;

    @GetMapping
    public List<MovieDto> getMovieList() {
        List<Movie> movies = _service.getAllMovies();
        return movies.stream()
                .map(movie -> _dto.toMovieDto(movie))
                .toList();
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable Long id) {
        Optional<Movie> movie = _service.getMovieById(id);

        if (movie.isPresent())
            return movie.get();
        else
            return null;
    }

}
