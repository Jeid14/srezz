package com.srezz.modelDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Value
public class GroupDto {
    @NotBlank(message = "Invalid name!")
    String name;
    @Min(value = 1800, message = "Year creation must be no less than 1800")
    @Max(value = 2022, message = "Year creation must be less than 2022")
    int creationYear;
    @Min(value = 1800, message = "Year decay must be no less than 1800")
    @Max(value = 2022, message = "Year decay must be less than 2022")
    int decayYear;
    Set<AlbumForUser> albums;

    public GroupDto (@JsonProperty("name") String name,
                         @JsonProperty("creationYear") short creationYear,
                         @JsonProperty("decayYear") short decayYear,
                         @JsonProperty("availableAlbums") Set<AlbumForUser> albums) {
        this.name = name;
        this.creationYear = creationYear;
        this.decayYear = decayYear;
        this.albums = albums;
    }
}
