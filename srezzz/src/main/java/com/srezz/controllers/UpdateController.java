package com.srezz.controllers;

import com.srezz.exception.InvalidInputDataException;
import com.srezz.modelDto.MusicGroupUpdateDto;
import com.srezz.services.IMusicGroupService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import static com.srezz.utils.Mappings.UPDATE_GROUP;

@RestController
public class UpdateController {
    private final IMusicGroupService musicGroupService;

    public UpdateController(IMusicGroupService musicGroupService) {
        this.musicGroupService = musicGroupService;
    }

    @PatchMapping(UPDATE_GROUP)
    public void updateMusicGroup(@Valid @RequestBody MusicGroupUpdateDto musicGroupUpdateDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidInputDataException(bindingResult);
        }
        musicGroupService.editMusicGroup(musicGroupUpdateDto);

    }
}
