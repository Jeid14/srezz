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

@Repository
@HqlQualifier
@ConditionalOnProperty(name = "db.connector", havingValue = "hibernateHql")
public class MusicGroupHibernateHqlRepo implements IMusicGroupHibernateRepo {
    private final String NAME_PARAMETER = "name";
    public static final String SELECT_ALBUM_BY_NAME = "FROM Album WHERE LOWER(name) IN (:name)";
    public static final String SELECT_ALBUM_BY_NAME_AND_MUSIC_GROUP = "FROM Album WHERE LOWER(name) = LOWER(:name) AND musicGroup = :musicGroup";
    public static final String SELECT_GENRE_BY_NAME = "FROM Genre WHERE LOWER(name) = LOWER(:name)";
    public static final String SELECT_MUSIC_GROUP_BY_NAME = "FROM MusicGroup WHERE LOWER(name) IN (:name)";
    private final SessionFactory sessionFactory;

    public MusicGroupHibernateHqlRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void save(MusicGroup musicGroup) {

    }

    @Override
    public List<MusicGroup> findByNames(Set<String> names) {
        Session session = sessionFactory.getCurrentSession();
        Query<MusicGroup> musicGroupQuery = session.createQuery(SELECT_MUSIC_GROUP_BY_NAME, MusicGroup.class);
        musicGroupQuery.setParameterList(NAME_PARAMETER, names);
        return musicGroupQuery.list();
    }

    @Override
    public Optional<MusicGroup> findByName(String oldName) {
        return null;
    }
}
