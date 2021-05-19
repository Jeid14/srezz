package com.srezz.controllers;

import com.srezz.modelDto.MusicGroupDto;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UpdateController {

    @PatchMapping("/updateGroup")
    public void updateMusicGroup(@Valid @RequestBody MusicGroupDto musicGroupDto,){

    }
}
