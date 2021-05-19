package com.srezz.services.impl;

import com.srezz.modelDto.GroupDto;
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
        System.out.println(names);
        musicGroupRepo.findByNames(names.stream().map(String::toLowerCase).collect(Collectors.toSet()))
                .forEach(System.out::println);
        return musicGroupRepo.findByNames(names.stream().map(String::toLowerCase).collect(Collectors.toSet()))
                .stream()
                .map(entity -> new GroupDto(entity.getName(), entity.getYearCreation(), entity.getYearDecay(),
                        entity.getAlbums()))
                .collect(Collectors.toSet());
    }

}
