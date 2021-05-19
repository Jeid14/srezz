package com.srezz.repository.hibernate.impl;

import com.srezz.entity.MusicGroup;
import com.srezz.qualifier.JpaQualifier;
import com.srezz.repository.hibernate.IMusicGroupHibernateRepo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@JpaQualifier
@ConditionalOnProperty(name = "db.connector", havingValue = "hibernateJpa")
public class MusicGroupHibernateJpql implements IMusicGroupHibernateRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    public MusicGroupHibernateJpql(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(MusicGroup musicGroup) {
//        String jqlQuery = "INSERT ";
//        TypedQuery<MusicGroup> query = entityManager.createQuery(jqlQuery, MusicGroup.class);
    }

    @Override
    @Transactional
    public List<MusicGroup> findByNames(Set<String> names) {
        String jqlQuery = "SELECT mg FROM MusicGroup mg WHERE lower(name) IN (:name) AND mg.id in (SELECT a.musicGroup FROM Album a)";
        TypedQuery<MusicGroup> query = entityManager.createQuery(jqlQuery, MusicGroup.class);
        query.setParameter("name", names);
        return query.getResultList();
    }

    @Override
    public Optional<MusicGroup> findByName(String oldName) {
        String jqlQuery = "SELECT mg FROM MusicGroup mg WHERE lower(name) = lower(:name) AND mg.id in (SELECT a.musicGroup FROM Album a)";
        TypedQuery<MusicGroup> query = entityManager.createQuery(jqlQuery, MusicGroup.class);
        query.setParameter("name", oldName);
       return query.getResultStream().findAny();
    }

}
