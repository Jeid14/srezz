package com.srezz.modelDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicGroupSaveDto {
    @NotBlank(message = "Invalid name!")
    String name;
    @Min(value = 1800, message = "Year creation must be no less than 1800")
    @Max(value = 2022, message = "Year creation must be less than 2022")
    short creationYear;
    Short decayYear;
}
