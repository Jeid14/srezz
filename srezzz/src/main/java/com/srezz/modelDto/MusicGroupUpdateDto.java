package com.srezz.modelDto;

import lombok.Value;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Value
public class MusicGroupUpdateDto {
    private static final String INVALID_NAME = "Invalid name!";
    private static final String MAX_CREATING_WARNING = "Year creation must be less than 2022";
    private static final String MAX_DECAY_WARNING = "Year decay must be less than 2022";

    @NotBlank(message = INVALID_NAME)
    String oldName;
    String newName;
//    @Min(value = 1800, message = "Year creation must be no less than 1800")
    @Max(value = 2022, message = MAX_CREATING_WARNING)
    int creationYear;
//    @Min(value = 1800, message = "Year decay must be no less than 1800")
    @Max(value = 2022, message = MAX_DECAY_WARNING)
    int decayYear;
//    @Min(value = 0, message = "Available albums must be no less 0")
    int availableAlbums;
}
