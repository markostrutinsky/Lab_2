package com.strutynskyi.api.services.interfaces;

import com.strutynskyi.api.models.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    Movie findById(Long id);
    List<Movie> findByDirector(String firstName, String lastName);
    Movie save(Movie movie);
    Movie update(Long movieId ,Movie movie);
    Movie delete(Long id);
}
