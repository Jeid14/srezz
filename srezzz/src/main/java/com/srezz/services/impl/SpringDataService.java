package com.srezz.services.impl;

import com.srezz.entity.MusicGroup;
import com.srezz.exception.GroupNotFoundException;
import com.srezz.modelDto.AlbumForUser;
import com.srezz.modelDto.GroupDto;
import com.srezz.modelDto.MusicGroupSaveDto;
import com.srezz.modelDto.MusicGroupUpdateDto;
import com.srezz.repository.jpa.IGroupRepo;
import com.srezz.services.IMusicGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "db.connector", havingValue = "springData")
public class SpringDataService implements IMusicGroupService {
    private final IGroupRepo groupRepo;

    @Autowired
    public SpringDataService(IGroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    @Override
    public void editMusicGroup(MusicGroupUpdateDto musicGroupUpdateDto) {
        MusicGroup musicGroup = groupRepo.findByName(musicGroupUpdateDto.getOldName()).orElseThrow(() -> new GroupNotFoundException("Group with name " + musicGroupUpdateDto.getOldName() + " not found"));
        updateMusicGroup(musicGroupUpdateDto, musicGroup);
    }

    @Override
    public void addMusicGroup(MusicGroupSaveDto musicGroupSaveDto) {
        MusicGroup group = new MusicGroup(null, musicGroupSaveDto.getName(), musicGroupSaveDto.getCreationYear(), musicGroupSaveDto.getDecayYear());
        groupRepo.save(group);
    }

    @Override
    public Set<GroupDto> getMusicGroups(Set<String> names) {
        return groupRepo.findByNameInIgnoreCase(names.stream().map(String::toLowerCase).collect(Collectors.toSet()))
                .stream()
                .map(entity -> new GroupDto(entity.getName(), entity.getYearCreation(), entity.getYearDecay() == null ? 0 : entity.getYearDecay(), entity.getAlbums()
                        .stream()
                        .map(album -> new AlbumForUser(album.getName(), album.getYearRelease(), album.getGenre().getName())).collect(Collectors.toSet())))
                .collect(Collectors.toSet());

    }

    private void updateMusicGroup(MusicGroupUpdateDto musicGroupUpdateDto, MusicGroup musicGroup) {
        if (musicGroupUpdateDto.getNewName() != null) {
            musicGroup.setName(musicGroupUpdateDto.getNewName());
        }
        if (musicGroupUpdateDto.getCreationYear() != null) {
            musicGroup.setYearCreation(musicGroupUpdateDto.getCreationYear());
        }
        if (musicGroupUpdateDto.getDecayYear() != null) {
            musicGroup.setYearDecay(musicGroupUpdateDto.getDecayYear());
        }
        System.out.println(musicGroup);
        groupRepo.save(musicGroup);
    }
}
