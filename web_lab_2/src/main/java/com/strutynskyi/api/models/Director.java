package com.strutynskyi.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name: filled incorrectly. It cannot consist only of spaces or be left unfilled.")
    private String firstName;

    @NotBlank(message = "Last name: filled incorrectly. It cannot consist only of spaces or be left unfilled.")
    private String lastName;

    private String biography;

    @NotNull(message = "Date cannot be left unfilled.")
    @Past(message = "Date must be in the past.")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Movie> movies = new ArrayList<>();

    public Director(String firstName, String lastName, String biography, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return Objects.equals(firstName.toLowerCase(), director.firstName.toLowerCase())
                && Objects.equals(lastName.toLowerCase(), director.lastName.toLowerCase())
                && Objects.equals(birthDate, director.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName.toLowerCase(), lastName.toLowerCase(), birthDate);
    }
}
