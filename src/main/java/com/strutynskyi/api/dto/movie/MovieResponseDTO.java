package com.strutynskyi.api.dto.movie;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MovieResponseDTO {
    private String title;
    private Long directorId;

    @JsonCreator
    public MovieResponseDTO(
            @JsonProperty("title") String title,
            @JsonProperty("directorId") Long directorId
    ) {
        this.title = title;
        this.directorId = directorId;
    }
}
