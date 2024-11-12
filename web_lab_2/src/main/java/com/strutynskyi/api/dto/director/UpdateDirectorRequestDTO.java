package com.strutynskyi.api.dto.director;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateDirectorRequestDTO {
    private String firstName;
    private String lastName;
    private String biography;
    private LocalDate birthDate;

    @JsonCreator
    public UpdateDirectorRequestDTO(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("biography") String biography,
            @JsonProperty("birthDate") LocalDate birthDate
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.birthDate = birthDate;
    }
}
