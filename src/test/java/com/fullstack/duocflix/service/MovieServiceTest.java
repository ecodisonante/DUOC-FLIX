package com.fullstack.duocflix.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.fullstack.duocflix.model.Movie;
import com.fullstack.duocflix.repository.MovieRepository;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @InjectMocks
    private MovieService service;
    @Mock
    private MovieRepository repository;

    private List<Movie> movies;

    @Test
    void testGetAllMovies() {
        // given
        movies = List.of(
                new Movie(1, "title1", 2024, "director1", "genre1", "synopsis1"),
                new Movie(2, "title2", 2024, "director2", "genre2", "synopsis2"),
                new Movie(3, "title3", 2024, "director3", "genre3", "synopsis3"));

        // when
        when(repository.findAll()).thenReturn(movies);
        var result = service.getAllMovies();

        // then
        assertEquals(movies.size(), result.size());
    }

    @Test
    void testGetMovieById() {
        // given
        var movie = new Movie(5, "title5", 2024, "director5", "genre5", "synopsis5");

        // when
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        when(repository.findById(5L)).thenReturn(Optional.of(movie));

        // then
        assertTrue(service.getMovieById(100L).isEmpty());
        assertTrue(service.getMovieById(5L).isPresent());
    }

    @Test
    void testCreateMovie() throws Exception {
        // given
        var movie = new Movie(5, "title5", 2024, "director5", "genre5", "synopsis5");

        // when
        when(repository.save(any())).thenReturn(movie);
        var result = service.createMovie(movie);

        // then
        assertEquals(movie.getTitle(), result.getTitle());
        assertThrows(Exception.class, () -> service.createMovie(new Movie()));
    }

    @Test
    void testUpdateMovie() throws Exception {
        // given
        var movie = new Movie(5, "title5", 2024, "director5", "genre5", "synopsis5");

        // when
        when(repository.existsById(anyLong())).thenReturn(false);
        when(repository.existsById(5L)).thenReturn(true);
        when(repository.save(any())).thenReturn(movie);

        var result = service.updateMovie(5L, movie);

        // then
        assertEquals(movie.getTitle(), result.getTitle());
        assertNull(service.updateMovie(2L, movie));
        assertThrows(Exception.class, () -> service.updateMovie(5L, new Movie()));
    }

    @Test
    void testDeleteMovie() {
        // given
        when(repository.existsById(anyLong())).thenReturn(false);
        when(repository.existsById(5L)).thenReturn(true);

        // when
        service.deleteMovie(2L);
        service.deleteMovie(5L);

        // then
        verify(repository, times(1)).deleteById(any());

    }

    @Test
    void testIsValidMovie() {
        // given
        var movie = new Movie();

        // then
        assertThrows(Exception.class, () -> service.createMovie(movie), "Título no ingresado");
        movie.setTitle("Titulo");
        assertThrows(Exception.class, () -> service.createMovie(movie), "Año no ingresado");
        movie.setYear(2024);
        assertThrows(Exception.class, () -> service.createMovie(movie), "Director no ingresado");
        movie.setDirector("Director");
        assertThrows(Exception.class, () -> service.createMovie(movie), "Género no ingresado");
        movie.setGenre("Genero");
        assertThrows(Exception.class, () -> service.createMovie(movie), "Sinópsis no ingresada");
    }
}
