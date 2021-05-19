package com.srezz.services.impl;

import com.srezz.qualifier.JpaQualifier;
import com.srezz.repository.hibernate.IMusicGroupHibernateRepo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service("musicGroupJpqlService")
@ConditionalOnProperty(name = "db.connector", havingValue = "hibernateJpa")
public class MusicGroupJpqlService extends HibernateCriteriaGroupService {

    public MusicGroupJpqlService(@JpaQualifier IMusicGroupHibernateRepo musicGroupRepo) {
        super(musicGroupRepo);
    }


}

