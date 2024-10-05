package com.strutynskyi.api.controllers;

import com.strutynskyi.api.dto.movie.MovieResponseDTO;
import com.strutynskyi.api.mappers.MovieMappers;
import com.strutynskyi.api.dto.movie.CreateMovieRequestDTO;
import com.strutynskyi.api.dto.movie.MovieDTO;
import com.strutynskyi.api.dto.movie.UpdateMovieRequestDTO;
import com.strutynskyi.api.exceptions.MovieAlreadyExistsException;
import com.strutynskyi.api.exceptions.NoSuchDirectorException;
import com.strutynskyi.api.exceptions.NoSuchMovieException;
import com.strutynskyi.api.models.Director;
import com.strutynskyi.api.models.Movie;
import com.strutynskyi.api.services.interfaces.DirectorService;
import com.strutynskyi.api.services.interfaces.MovieService;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final DirectorService directorService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<MovieDTO> movies = movieService.findAll()
                .stream()
                .map(MovieMappers::toMovieDTO)
                .toList();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Movie existingMovie = movieService.findById(id);
        MovieDTO movieDTO = MovieMappers.toMovieDTO(existingMovie);
        return ResponseEntity.ok(movieDTO);
    }

    @GetMapping("/by-director")
    public ResponseEntity<?> getByDirector(@RequestParam String firstName, @RequestParam String lastName) {
        List<Movie> movies = movieService.findByDirector(firstName, lastName);
        List<MovieResponseDTO> moviesResponseDTO = movies
                .stream()
                .map(MovieMappers::toMovieResponseDTOFromMovie)
                .toList();
        return new ResponseEntity<>(moviesResponseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createMovie(@RequestBody CreateMovieRequestDTO movieDTO) {
        Director existingDirector = directorService.findById(movieDTO.getDirectorId());
        Movie movieModel = MovieMappers.toMovieFromCreateDTO(movieDTO);
        movieModel.setDirector(existingDirector);
        Movie saved = movieService.save(movieModel);
        return new ResponseEntity<>(MovieMappers.toMovieDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<?> updateMovie(@PathVariable Long movieId, @RequestBody UpdateMovieRequestDTO movieDTO) {
        Movie movieFromUpdateDTO = MovieMappers.toMovieFromUpdateDTO(movieDTO);
        Movie updatedMovieDTO = movieService.update(movieId, movieFromUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(MovieMappers.toMovieDTO(updatedMovieDTO));
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long movieId) {
        Movie deletedMovie = movieService.delete(movieId);
        return new ResponseEntity<>("Movie was deleted by id: " + deletedMovie.getId(), HttpStatus.OK);
    }
}
