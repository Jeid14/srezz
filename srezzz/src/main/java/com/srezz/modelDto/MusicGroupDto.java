package com.srezz.modelDto;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class MusicGroupDto {
    @NotBlank(message = "Invalid name!")
    String name;
    int creationYear;
    int decayYear;
    int availableAlbums;
    List<String> genres;
}
