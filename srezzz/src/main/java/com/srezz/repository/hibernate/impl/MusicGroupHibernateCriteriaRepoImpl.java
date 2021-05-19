package com.srezz.repository.hibernate.impl;

import com.srezz.entity.MusicGroup;
import com.srezz.qualifier.CriteriaQualifier;
import com.srezz.repository.hibernate.IMusicGroupHibernateRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@CriteriaQualifier
@ConditionalOnProperty(name = "db.connector", havingValue = "hibernateCriteria")
public class MusicGroupHibernateCriteriaRepoImpl implements IMusicGroupHibernateRepo {
    private final String NAME_PARAMETER = "name";
    private final SessionFactory sessionFactory;

    public MusicGroupHibernateCriteriaRepoImpl(SessionFactory sessionFactory) {
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
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<MusicGroup> criteriaQuery = builder.createQuery(MusicGroup.class);
        Root<MusicGroup> root = criteriaQuery.from(MusicGroup.class);
        criteriaQuery.select(root).where(builder.lower(root.get(NAME_PARAMETER)).in(names));
        return session.createQuery(criteriaQuery).list();
    }

    @Override
    public Optional<MusicGroup> findByName(String oldName) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<MusicGroup> criteriaQuery = builder.createQuery(MusicGroup.class);
        Root<MusicGroup> root = criteriaQuery.from(MusicGroup.class);
        criteriaQuery.select(root).where(builder.equal(builder.lower(root.get(NAME_PARAMETER)), oldName.toLowerCase()));
        return session.createQuery(criteriaQuery).uniqueResultOptional();
    }
}
