package com.strutynskyi.api.dto.director;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.strutynskyi.api.dto.movie.MovieDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DirectorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String biography;
    private LocalDate birthDate;
    private List<MovieDTO> movies;

    @JsonCreator
    public DirectorDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("biography") String biography,
            @JsonProperty("birthDate") LocalDate birthDate,
            @JsonProperty("movies") List<MovieDTO> movies
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.birthDate = birthDate;
        this.movies = movies;
    }
}
