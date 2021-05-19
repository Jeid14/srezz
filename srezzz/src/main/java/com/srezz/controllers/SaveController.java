package com.srezz.controllers;

import com.srezz.exception.InvalidInputDataException;
import com.srezz.modelDto.MusicGroupSaveDto;
import com.srezz.services.IMusicGroupService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.srezz.utils.Mappings.SAVE_GROUP;

@RestController
public class SaveController {
    private final IMusicGroupService musicGroupService;

    public SaveController(IMusicGroupService musicGroupService) {
        this.musicGroupService = musicGroupService;
    }

    @PostMapping(SAVE_GROUP)
    public void saveMusicGroup(@Valid @RequestBody MusicGroupSaveDto musicGroupSaveDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidInputDataException(bindingResult);
        }
        musicGroupService.addMusicGroup(musicGroupSaveDto);

    }
}
