package com.strutynskyi.api.dto.movie;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;

@Data
public class UpdateMovieRequestDTO {
    private String title;
    private String genre;
    private LocalDate releaseDate;
    private Duration duration;

    @JsonCreator
    public UpdateMovieRequestDTO(
            @JsonProperty("title") String title,
            @JsonProperty("genre") String genre,
            @JsonProperty("releaseDate") LocalDate releaseDate,
            @JsonProperty("duration") Duration duration
    ) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }
}
