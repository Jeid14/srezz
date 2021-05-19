package com.srezz.services.impl;


import com.srezz.modelDto.GroupDto;
import com.srezz.modelDto.MusicGroupSaveDto;
import com.srezz.modelDto.MusicGroupUpdateDto;
import com.srezz.qualifier.CriteriaQualifier;
import com.srezz.repository.hibernate.IMusicGroupHibernateRepo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("hibernateCriteriaGroupService")
@ConditionalOnProperty(name = "db.connector", havingValue = "hibernateCriteria")
public class HibernateCriteriaGroupService extends HibernateMusicGroupBase {

    public HibernateCriteriaGroupService(@CriteriaQualifier IMusicGroupHibernateRepo musicGroupRepo) {
        super(musicGroupRepo);
    }

}
