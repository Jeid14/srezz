package com.srezz.services.impl;

import com.srezz.modelDto.MusicGroupSaveDto;
import com.srezz.modelDto.MusicGroupUpdateDto;
import com.srezz.qualifier.HqlQualifier;
import com.srezz.repository.hibernate.IMusicGroupHibernateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service("hibernateHqlAlbumService")
@ConditionalOnProperty(name = "db.connector", havingValue = "hibernateHql")
public class HibernateHqlAlbumService extends HibernateMusicGroupBase {

    @Autowired
    public HibernateHqlAlbumService(@HqlQualifier IMusicGroupHibernateRepo musicGroupRepo) {
        super(musicGroupRepo);
    }

    @Override
    public void editMusicGroup(MusicGroupUpdateDto musicGroupUpdateDto) {

    }

    @Override
    public void addMusicGroup(MusicGroupSaveDto musicGroupSaveDto) {

    }
}
