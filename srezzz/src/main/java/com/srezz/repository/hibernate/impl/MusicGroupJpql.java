package com.srezz.repository.hibernate.impl;

import com.srezz.entity.MusicGroup;
import com.srezz.qualifier.JpaQualifier;
import com.srezz.repository.hibernate.IMusicGroupHibernateRepo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
@JpaQualifier
@ConditionalOnProperty(name = "db.connector", havingValue = "hibernateJpa")
public class MusicGroupJpql implements IMusicGroupHibernateRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    public MusicGroupJpql(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(MusicGroup musicGroup) {

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
    public List<MusicGroup> findAll() {
        String jqlQuery = "SELECT mg FROM MusicGroup mg  WHERE mg.id in (SELECT a.musicGroup FROM Album a)";
        Query query = entityManager.createQuery(jqlQuery);
        return query.getResultList();
    }
}
