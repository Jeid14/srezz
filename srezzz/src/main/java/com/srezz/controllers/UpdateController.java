package com.srezz.controllers;

import com.srezz.modelDto.MusicGroupUpdateDto;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import static com.srezz.utils.Mappings.UPDATE_GROUP;

@RestController
public class UpdateController {

    @PatchMapping(UPDATE_GROUP)
    public void updateMusicGroup(@Valid @RequestBody MusicGroupUpdateDto musicGroupUpdateDto, BindingResult bindingResult){

    }
}
