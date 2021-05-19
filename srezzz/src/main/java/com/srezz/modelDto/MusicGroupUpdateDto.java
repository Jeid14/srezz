package com.srezz.modelDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicGroupUpdateDto {
    private static final String INVALID_NAME = "Invalid name!";
    private static final String MAX_CREATING_WARNING = "Year creation must be less than 2022";
    private static final String MAX_DECAY_WARNING = "Year decay must be less than 2022";

    @NotBlank(message = INVALID_NAME)
    String oldName;
    String newName;
    @Max(value = 2022, message = MAX_CREATING_WARNING)
    Short creationYear;
    @Max(value = 2022, message = MAX_DECAY_WARNING)
    Short decayYear;
}
