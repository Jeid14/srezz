package com.srezz.repository.hibernate.impl;

import com.srezz.entity.MusicGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import javax.persistence.criteria.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static com.srezz.utils.HqlQuery.NAME_PARAMETER;

class MusicGroupHibernateCriteriaRepoImplTest {
    private final SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
    private final Session session = Mockito.mock(Session.class);
    private final CriteriaBuilder builder = Mockito.mock(CriteriaBuilder.class);
    private final CriteriaQuery<MusicGroup> query = Mockito.mock(CriteriaQuery.class);
    private final Root<MusicGroup> root = Mockito.mock(Root.class);
    private final Predicate predicate = Mockito.mock(Predicate.class);
    private final Path<Object> path = Mockito.mock(Path.class);
    private final Expression<String> expression = Mockito.mock(Expression.class);
    private final Query<MusicGroup> musicGroupQuery = Mockito.mock(Query.class);
    private final MusicGroupHibernateCriteriaRepoImpl cut = new MusicGroupHibernateCriteriaRepoImpl(sessionFactory);

    private static Stream<Arguments> saveTestSource() {
        return Stream.of(
                Arguments.arguments(new MusicGroup(null, "Смех", (short) 1999, null)),
                Arguments.arguments(new MusicGroup(null, "Hollywood Undead", (short) 2005, null)),
                Arguments.arguments(new MusicGroup(null, "t.A.T.u.", (short) 1999, (short) 2016))
        );
    }

    private static Stream<Arguments> findByNamesTestSource() {
        Set<String> stringSet1 = new HashSet<>();
        stringSet1.add("Смех");
        Set<String> stringSet2 = new HashSet<>();
        stringSet2.add("Hollywood Undead");
        stringSet2.add("t.A.T.u.");
        Set<String> stringSet3 = new HashSet<>();
        stringSet3.add("Slipknot");
        stringSet3.add("I Prevail");
        stringSet3.add("Pendulum");
        return Stream.of(
                Arguments.arguments(stringSet1),
                Arguments.arguments(stringSet2),
                Arguments.arguments(stringSet3)
        );
    }

    @ParameterizedTest
    @MethodSource("saveTestSource")
    void saveTest(MusicGroup musicGroup) {
        Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
        Mockito.when(sessionFactory.getCriteriaBuilder()).thenReturn(builder);
        Mockito.when(builder.createQuery(MusicGroup.class)).thenReturn(query);

        cut.save(musicGroup);

        Mockito.verify(session, Mockito.times(1)).save(musicGroup);
    }

    @ParameterizedTest
    @MethodSource("findByNamesTestSource")
    void findByNamesTest(Set<String> names) {
        Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
        Mockito.when(session.getCriteriaBuilder()).thenReturn(builder);
        Mockito.when(builder.createQuery(MusicGroup.class)).thenReturn(query);
        Mockito.when(query.from(MusicGroup.class)).thenReturn(root);
        Mockito.when(query.select(root)).thenReturn(query);
        Mockito.when(root.get(NAME_PARAMETER)).thenReturn(path);
        Mockito.when(builder.lower(root.get(NAME_PARAMETER))).thenReturn(expression);
        Mockito.when(builder.lower(root.get(NAME_PARAMETER)).in(names)).thenReturn(predicate);
        Mockito.when(query.where(builder.lower(root.get(NAME_PARAMETER)).in(names))).thenReturn(query);
        Mockito.when(session.createQuery(query)).thenReturn(musicGroupQuery);

        cut.findByNames(names);

        Mockito.verify(musicGroupQuery, Mockito.times(1)).list();
    }
}