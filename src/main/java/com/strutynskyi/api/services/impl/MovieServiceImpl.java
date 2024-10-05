package com.strutynskyi.api.services.impl;

import com.strutynskyi.api.exceptions.MovieAlreadyExistsException;
import com.strutynskyi.api.exceptions.NoSuchMovieException;
import com.strutynskyi.api.models.Movie;
import com.strutynskyi.api.repositories.MovieRepository;
import com.strutynskyi.api.services.interfaces.MovieService;
import com.strutynskyi.api.validators.EntityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final EntityValidator<Movie> movieValidator;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchMovieException(id));
    }

    @Override
    public List<Movie> findByDirector(String firstName, String lastName) {
        return movieRepository.findByDirector(firstName, lastName);
    }

    @Override
    public Movie save(Movie movieToSave) {
        movieValidator.validate(movieToSave);
        Optional<Movie> existingDirector = movieRepository.findAll()
                .stream()
                .filter(d -> d.equals(movieToSave))
                .findFirst();

        if (existingDirector.isPresent())
            throw new MovieAlreadyExistsException(existingDirector.get().getId());
        return movieRepository.save(movieToSave);
    }

    @Override
    public Movie update(Long id, Movie movieToUpdate) {
        movieValidator.validate(movieToUpdate);

        Movie foundMovie = movieRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchMovieException(id));

        foundMovie.setTitle(movieToUpdate.getTitle());
        foundMovie.setGenre(movieToUpdate.getGenre());
        foundMovie.setReleaseDate(movieToUpdate.getReleaseDate());
        foundMovie.setDuration(movieToUpdate.getDuration());

        return movieRepository.save(foundMovie);
    }

    @Override
    public Movie delete(Long id) {
        Movie movieToDelete = movieRepository.findById(id)
                .orElseThrow(() -> new NoSuchMovieException(id));
        movieRepository.deleteById(id);
        return movieToDelete;
    }
}
