package com.srezz.services.impl;

import com.srezz.modelDto.GroupDto;
import com.srezz.modelDto.MusicGroupSaveDto;
import com.srezz.modelDto.MusicGroupUpdateDto;
import com.srezz.qualifier.CriteriaQualifier;
import com.srezz.qualifier.JpaQualifier;
import com.srezz.repository.hibernate.IMusicGroupHibernateRepo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service("musicGroupJpqlService")
@ConditionalOnProperty(name = "db.connector", havingValue = "hibernateJpa")
public class MusicGroupJpqlService extends HibernateCriteriaGroupService {

    public MusicGroupJpqlService(@JpaQualifier IMusicGroupHibernateRepo musicGroupRepo) {
        super(musicGroupRepo);
    }


}

