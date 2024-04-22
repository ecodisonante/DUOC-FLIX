package com.fullstack.duocflix.model;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "movie")
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends RepresentationModel<Movie>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "year")
    private int year;
    @Column(name = "director")
    private String director;
    @Column(name = "genre")
    private String genre;
    @Lob
    @Column(name = "synopsis")
    private String synopsis;

    public MovieDto toDto() {
        return new MovieDto(id, title, year);
    }

}
