package com.fullstack.duocflix.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.fullstack.duocflix.model.Movie;
import com.fullstack.duocflix.service.MovieService;

@ExtendWith(MockitoExtension.class)
class MovieControllerTest {

    @InjectMocks
    private MovieController controller;

    @Mock
    private MovieService service;

    @Test
    void testGetMovieList() {
        // given
        var movies = List.of(
                new Movie(1, "title1", 2024, "director1", "genre1", "synopsis1"),
                new Movie(2, "title2", 2024, "director2", "genre2", "synopsis2"),
                new Movie(3, "title3", 2024, "director3", "genre3", "synopsis3"));

        when(service.getAllMovies()).thenReturn(movies);

        // test OK
        var result = controller.getMovieList();
        assertEquals(HttpStatus.OK, result.getStatusCode());

        // Test Empty
        when(service.getAllMovies()).thenReturn(new ArrayList<Movie>());
        result = controller.getMovieList();
        assertEquals(HttpStatus.OK, result.getStatusCode());

        // Test Catch
        when(service.getAllMovies()).thenThrow(new RuntimeException("Exception"));
        result = controller.getMovieList();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    }

    @Test
    void testGetMovie() {
        // given
        var movie = new Movie(1, "title1", 2024, "director1", "genre1", "synopsis1");
        when(service.getMovieById(1L)).thenReturn(Optional.of(movie));

        // test OK
        var result = controller.getMovie(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(Movie.class, result.getBody().getClass());

        // Test Empty
        result = controller.getMovie(500L);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

        // Test Catch
        when(service.getMovieById(anyLong())).thenThrow(new RuntimeException("Exception"));
        result = controller.getMovie(1L);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    }

    @Test
    void testCreateMovie() throws Exception {
        // given
        var movie = new Movie(1, "title1", 2024, "director1", "genre1", "synopsis1");
        when(service.createMovie(movie)).thenReturn(movie);

        // test OK
        var result = controller.createMovie(movie);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(Movie.class, result.getBody().getClass());

        // Test Catch
        when(service.createMovie(any())).thenThrow(new RuntimeException("Exception"));
        result = controller.createMovie(movie);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void testUpdateMovie() throws Exception {
        // given
        var movie = new Movie(5L, "title1", 2024, "director1", "genre1", "synopsis1");
        when(service.updateMovie(5L, movie)).thenReturn(movie);

        // test OK
        var result = controller.updateMovie(5L, movie);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(Movie.class, result.getBody().getClass());

        // Test Catch
        when(service.updateMovie(anyLong(), any())).thenThrow(new RuntimeException("Exception"));
        result = controller.updateMovie(5L, movie);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void testDeleteMovie() {
        // given
        when(service.deleteMovie(anyLong())).thenReturn(false);
        when(service.deleteMovie(5L)).thenReturn(true);
        when(service.deleteMovie(null)).thenThrow(new RuntimeException("Exception"));

        // test OK
        var result = controller.deleteMovie(5L);
        assertEquals(HttpStatus.OK, result.getStatusCode());

        // test Fail
        result = controller.deleteMovie(100L);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

        // Test Catch
        result = controller.deleteMovie(null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    }


}
