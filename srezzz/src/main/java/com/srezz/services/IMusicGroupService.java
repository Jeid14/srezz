package com.srezz.services;

import com.srezz.modelDto.GroupDto;
import com.srezz.modelDto.MusicGroupSaveDto;
import com.srezz.modelDto.MusicGroupUpdateDto;
import java.util.Set;

public interface IMusicGroupService {
    void editMusicGroup(MusicGroupUpdateDto musicGroupUpdateDto);

    void addMusicGroup(MusicGroupSaveDto musicGroupSaveDto);

    Set<GroupDto> getMusicGroups(Set<String> names);


}
