package com.srezz.modelDto;

import lombok.Value;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Value
public class MusicGroupSaveDto {
    @NotBlank(message = "Invalid name!")
    String name;
    @Min(value = 1800, message = "Year creation must be no less than 1800")
    @Max(value = 2022, message = "Year creation must be less than 2022")
    int creationYear;
    @Min(value = 1800, message = "Year decay must be no less than 1800")
    @Max(value = 2022, message = "Year decay must be less than 2022")
    int decayYear;
    @Min(value = 0, message = "Available albums must be no less 0")
    int availableAlbums;
}
