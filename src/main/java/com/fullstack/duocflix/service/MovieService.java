package com.fullstack.duocflix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.duocflix.model.Movie;
import com.fullstack.duocflix.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IMovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public Movie createMovie(Movie movie) throws Exception {

        String valid = isvalidMovie(movie);
        if (!valid.isEmpty())
            throw new Exception(valid);

        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Long id, Movie movie) throws Exception {

        if (movieRepository.existsById(id)) {

            String valid = isvalidMovie(movie);
            if (!valid.isEmpty())
                throw new Exception(valid);

            movie.setId(id);

            return movieRepository.save(movie);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteMovie(Long id) {

        if (movieRepository.existsById(id)) {

            movieRepository.deleteById(id);

            return true;
        } else {
            return false;
        }
    }

    private String isvalidMovie(Movie movie) {
        if (movie.getTitle() == null || movie.getTitle().isEmpty())
            return "Título no ingresado";
        if (movie.getYear() == 0)
            return "Año no ingresado";
        if (movie.getDirector() == null || movie.getDirector().isEmpty())
            return "Director no ingresado";
        if (movie.getGenre() == null || movie.getGenre().isEmpty())
            return "Género no ingresado";
        if (movie.getSynopsis() == null || movie.getSynopsis().isEmpty())
            return "Sinópsis no ingresada";

        return "";
    }
}
