package com.srezz.modelDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlbumForUser {
    public String name;
    public short releaseYear;
    public String genre;
}
