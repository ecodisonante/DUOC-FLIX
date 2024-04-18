package com.fullstack.duocflix.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fullstack.duocflix.model.Movie;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MovieRepositoryTest {
    @Autowired
    private MovieRepository repository;

    @BeforeEach
    public void setup() {
        // given
        repository.deleteAll();
        repository.saveAllAndFlush(List.of(
                new Movie(0, "title1", 2024, "director1", "genre1", "synopsis1"),
                new Movie(0, "title2", 2024, "director2", "genre2", "synopsis2"),
                new Movie(0, "title3", 2024, "director3", "genre3", "synopsis3"),
                new Movie(0, "title4", 2024, "director4", "genre4", "synopsis4"),
                new Movie(0, "title5", 2024, "director5", "genre5", "synopsis5")));
    }

    @Test
    void getAllData(){
        // when
        var movies = repository.findAll();
        // then
        assertEquals(5, movies.size());
    }

    @Test
    void createMovie() {
        // when
        Movie movie = new Movie(0, "title6", 2024, "director6", "genre6", "synopsis6");
        var result = repository.save(movie);
        // then
        assertTrue(result.getId() > 0);
        assertTrue(repository.existsById(result.getId()));
    }

    @Test
    void deleteMovie() {
        // when
        var movie = repository.findAll().getFirst();
        repository.deleteById(movie.getId());
        // then
        assertFalse(repository.existsById(movie.getId()));
    }


}
