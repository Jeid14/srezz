package com.srezz.controllers;

import com.srezz.exception.InvalidInputDataException;
import com.srezz.modelDto.ErrorDto;
import com.srezz.modelDto.GroupDto;
import com.srezz.services.IMusicGroupService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;
import static com.srezz.utils.ExceptionsMessage.EMPTY_PARAMETER;
import static com.srezz.utils.Fields.REQUEST_PARAMETER;
import static com.srezz.utils.Mappings.READ_ALL;
import static com.srezz.utils.Mappings.READ_BY_NAME;

@RestController
public class FindController {
    private final IMusicGroupService musicGroupService;

    public FindController( IMusicGroupService musicGroupService) {
        this.musicGroupService = musicGroupService;
    }

    @GetMapping(READ_BY_NAME)
    public Set<GroupDto> getAllByNames(@RequestParam(name = "name") Set<String> groupsName){
        if (groupsName.stream().anyMatch(StringUtils::isEmpty)) {
            throw new InvalidInputDataException(new ErrorDto(REQUEST_PARAMETER, null, EMPTY_PARAMETER));
        }

        Set<GroupDto> set = musicGroupService.getMusicGroups(groupsName);
        for (GroupDto s: set) {
            System.out.println(  s.toString());
        }
        return set;
    }
    @GetMapping(READ_ALL)
    public Set<GroupDto> getAll(){
        Set<GroupDto> set = musicGroupService.getAllMusicGroups();
        for (GroupDto s: set
        ) {
            System.out.println(  s.toString());
        }
        return set;
    }
}
