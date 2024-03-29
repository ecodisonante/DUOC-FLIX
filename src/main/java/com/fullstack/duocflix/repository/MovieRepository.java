package com.fullstack.duocflix.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.duocflix.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{
    
}
