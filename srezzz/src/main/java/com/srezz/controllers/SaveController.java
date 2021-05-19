package com.srezz.controllers;

import com.srezz.modelDto.MusicGroupSaveDto;
import com.srezz.modelDto.MusicGroupUpdateDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SaveController {
    @PostMapping("/saveMusicGroup")
    public void savaMusicGroup(@Valid @RequestBody MusicGroupSaveDto musicGroupSaveDto){
    }
}
