package com.fullstack.duocflix.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.fullstack.duocflix.model.Movie;
import com.fullstack.duocflix.model.MovieException;
import com.fullstack.duocflix.service.MovieService;

@ExtendWith(MockitoExtension.class)
class MovieControllerTest {

    @InjectMocks
    private MovieController controller;

    @Mock
    private MovieService service;

    private Movie movie;

    @BeforeEach
    void setup() {
        // given
        movie = new Movie(1, "title1", 2024, "director1", "genre1", "synopsis1");
    }

    @Test
    void testGetMovieList() {
        // given
        var movies = List.of(
                new Movie(1, "title1", 2024, "director1", "genre1", "synopsis1"),
                new Movie(2, "title2", 2024, "director2", "genre2", "synopsis2"),
                new Movie(3, "title3", 2024, "director3", "genre3", "synopsis3"));

        // when
        when(service.getAllMovies()).thenReturn(movies);
        var result = controller.getMovieList();

        // test OK
        assertNotNull(result.getContent());
        assertEquals(movies.size(), result.getContent().size());

        // Test Empty
        when(service.getAllMovies()).thenReturn(new ArrayList<Movie>());
        result = controller.getMovieList();
        assertTrue(result.getContent().isEmpty());
    }

    @Test
    void testGetMovie() {
        // when
        when(service.getMovieById(1L)).thenReturn(Optional.of(movie));
        var result = controller.getMovie(1L);

        // test OK
        assertNotNull(result.getContent());
        assertEquals(Movie.class, result.getContent().getClass());

        // test Error
        assertThrows(MovieException.class, () -> controller.getMovie(100L));
    }

    @Test
    void testCreateMovie() throws Exception {
        // when
        when(service.createMovie(new Movie())).thenThrow(new MovieException("Exception"));
        when(service.createMovie(movie)).thenReturn(movie);
        var result = controller.createMovie(movie);

        // test OK
        assertNotNull(result.getContent());
        assertEquals(Movie.class, result.getContent().getClass());

        // test Error
        assertThrows(MovieException.class, () -> controller.createMovie(new Movie()));
    }

    @Test
    void testUpdateMovie() throws Exception {
        // given
        when(service.updateMovie(100L, new Movie())).thenThrow(new MovieException("Exception"));
        when(service.updateMovie(5L, movie)).thenReturn(movie);
        var result = controller.updateMovie(5L, movie);

        // test OK
        assertNotNull(result.getContent());
        assertEquals(Movie.class, result.getContent().getClass());

        // test Error
        assertThrows(MovieException.class, () -> controller.updateMovie(100L, new Movie()));
    }

    @Test
    void testDeleteMovie() {
        // given
        when(service.deleteMovie(anyLong())).thenReturn(false);
        when(service.deleteMovie(5L)).thenReturn(true);
        // test OK
        var result = controller.deleteMovie(5L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        // test Fail
        assertThrows(MovieException.class, () -> controller.deleteMovie(100L));
    }

}
