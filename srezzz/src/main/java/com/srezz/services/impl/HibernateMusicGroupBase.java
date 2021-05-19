package com.srezz.services.impl;

import com.srezz.entity.MusicGroup;
import com.srezz.exception.GroupNotFoundException;
import com.srezz.modelDto.AlbumForUser;
import com.srezz.modelDto.GroupDto;
import com.srezz.modelDto.MusicGroupSaveDto;
import com.srezz.modelDto.MusicGroupUpdateDto;
import com.srezz.repository.hibernate.IMusicGroupHibernateRepo;
import com.srezz.services.IMusicGroupService;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class HibernateMusicGroupBase implements IMusicGroupService {
    private final IMusicGroupHibernateRepo musicGroupRepo;

    public HibernateMusicGroupBase(IMusicGroupHibernateRepo musicGroupRepo) {
        this.musicGroupRepo = musicGroupRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<GroupDto> getMusicGroups(Set<String> names) {
        return musicGroupRepo.findByNames(names.stream().map(String::toLowerCase).collect(Collectors.toSet()))
                .stream()
                .map(entity -> new GroupDto(entity.getName(), entity.getYearCreation(), entity.getYearDecay() == null ? 0 : entity.getYearDecay(), entity.getAlbums()
                        .stream()
                        .map(album -> new AlbumForUser(album.getName(), album.getYearRelease(), album.getGenre().getName())).collect(Collectors.toSet())))
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void editMusicGroup(MusicGroupUpdateDto musicGroupUpdateDto) {
        MusicGroup musicGroup = musicGroupRepo.findByName(musicGroupUpdateDto.getOldName())
                .orElseThrow(() -> new GroupNotFoundException("Group with name " + musicGroupUpdateDto.getOldName() + " not found"));
        updateMusicGroup(musicGroupUpdateDto, musicGroup);

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
    }

    @Override
    @Transactional
    public void addMusicGroup(MusicGroupSaveDto musicGroupSaveDto) {
        musicGroupRepo.save(new MusicGroup(null, musicGroupSaveDto.getName(), musicGroupSaveDto.getCreationYear(),
                musicGroupSaveDto.getDecayYear()));
    }


}
