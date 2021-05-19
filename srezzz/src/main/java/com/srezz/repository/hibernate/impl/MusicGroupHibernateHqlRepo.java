package com.srezz.repository.hibernate.impl;

import com.srezz.entity.MusicGroup;
import com.srezz.qualifier.HqlQualifier;
import com.srezz.repository.hibernate.IMusicGroupHibernateRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import static com.srezz.utils.HqlQuery.*;

@Repository
@HqlQualifier
@ConditionalOnProperty(name = "db.connector", havingValue = "hibernateHql")
public class MusicGroupHibernateHqlRepo implements IMusicGroupHibernateRepo {
    private final SessionFactory sessionFactory;
    private Query<MusicGroup> musicGroupQuery;

    public MusicGroupHibernateHqlRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(MusicGroup musicGroup) {
        Session session = sessionFactory.getCurrentSession();
        session.save(musicGroup);
    }

    @Override
    public List<MusicGroup> findByNames(Set<String> names) {
        Session session = sessionFactory.getCurrentSession();
        musicGroupQuery = session.createQuery(SELECT_MUSIC_GROUP_BY_NAMES, MusicGroup.class);
        musicGroupQuery.setParameterList(NAME_PARAMETER, names);
        return musicGroupQuery.list();
    }

    @Override
    public Optional<MusicGroup> findByName(String oldName) {
        Session session = sessionFactory.getCurrentSession();
        musicGroupQuery = session.createQuery(SELECT_MUSIC_GROUP_BY_NAME, MusicGroup.class);
        musicGroupQuery.setParameter(NAME_PARAMETER, oldName);
        return musicGroupQuery.uniqueResultOptional();
    }
}
