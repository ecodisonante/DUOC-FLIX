package com.fullstack.duocflix.service;

import com.fullstack.duocflix.model.Movie;
import java.util.List;
import java.util.Optional;

public interface IMovieService {

    List<Movie> getAllMovies();

    Optional<Movie> getMovieById(Long id);

    Movie createMovie(Movie movie) throws Exception;

    Movie updateMovie(Long id, Movie movie) throws Exception;

    boolean deleteMovie(Long id);

}
