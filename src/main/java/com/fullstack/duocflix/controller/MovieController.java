package com.fullstack.duocflix.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fullstack.duocflix.model.ExceptionResponse;
import com.fullstack.duocflix.model.Movie;
import com.fullstack.duocflix.model.MovieDto;
import com.fullstack.duocflix.model.MovieException;
import com.fullstack.duocflix.service.IMovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    IMovieService service;

    static final String ALL_MOVIES = "all-movies";

    @GetMapping
    public CollectionModel<EntityModel<MovieDto>> getMovieList() {
        List<Movie> movies = service.getAllMovies();

        var moviesResouce = movies.stream()
                .map(Movie::toDto)
                .map(movie -> EntityModel.of(movie,
                        WebMvcLinkBuilder
                                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMovie(movie.getId()))
                                .withSelfRel()))
                .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMovieList());
        return CollectionModel.of(moviesResouce, linkTo.withRel("movies"));
    }

    @GetMapping("/{id}")
    public EntityModel<Movie> getMovie(@PathVariable Long id) {
        Optional<Movie> movie = service.getMovieById(id);

        if (movie.isPresent())
            return EntityModel.of(movie.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMovie(id)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMovieList())
                            .withRel(ALL_MOVIES));

        else
            throw new MovieException("No existe la pelicula con el id " + id);
    }

    @PostMapping
    public EntityModel<Movie> createMovie(@RequestBody Movie movie) {
        try {
            var newMovie = service.createMovie(movie);
            return EntityModel.of(newMovie,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                            .getMovie(newMovie.getId())).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                            .getMovieList()).withRel(ALL_MOVIES));
        } catch (Exception e) {
            throw new MovieException("Error al crear pelicula: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public EntityModel<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        try {
            var updatedMovie = service.updateMovie(id, movie);
            return EntityModel.of(updatedMovie,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMovie(id)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMovieList())
                            .withRel(ALL_MOVIES));
        } catch (Exception e) {
            throw new MovieException("Error al actualizar pelicula: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable Long id) {
        if (service.deleteMovie(id)) {
            return ResponseEntity.ok(new ExceptionResponse(HttpStatus.OK.value(), "Película eliminada."));
        } else {
            throw new MovieException("No existe la pelicula con el id " + id);
        }
    }

}
