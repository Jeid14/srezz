package com.srezz.controllers;

import com.srezz.modelDto.MusicGroupSaveDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import static com.srezz.utils.Mappings.SAVE_GROUP;

@RestController
public class SaveController {
    @PostMapping(SAVE_GROUP)
    public void savaMusicGroup(@Valid @RequestBody MusicGroupSaveDto musicGroupSaveDto){
    }
}
