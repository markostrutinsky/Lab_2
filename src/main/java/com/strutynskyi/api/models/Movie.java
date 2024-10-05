package com.strutynskyi.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "This field was filled incorrectly. It cannot consist only of spaces or be left unfilled.")
    private String title;

    @NotBlank(message = "This field was filled incorrectly. It cannot consist only of spaces or be left unfilled.")
    private String genre;

    @NotNull(message = "Date cannot be left unfilled.")
    @Past(message = "Date must be in the past.")
    private LocalDate releaseDate;

    @NotNull(message = "Duration cannot be left unfilled.")
    private Duration duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    public Movie(String title, String genre, LocalDate releaseDate, Duration duration) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title.toLowerCase(), movie.title.toLowerCase())
                && Objects.equals(genre.toLowerCase(), movie.genre.toLowerCase())
                && Objects.equals(releaseDate, movie.releaseDate)
                && Objects.equals(duration, movie.duration)
                && Objects.equals(getDirector().getId(), movie.getDirector().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, releaseDate, duration, getDirector().getId());
    }
}
