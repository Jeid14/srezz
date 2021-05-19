package com.srezz.repository.hibernate.impl;

import com.srezz.entity.MusicGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import static com.srezz.utils.HqlQuery.*;

class MusicGroupHibernateHqlRepoTest {

    private final SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
    private final Session session = Mockito.mock(Session.class);
    private final MusicGroupHibernateHqlRepo cut = new MusicGroupHibernateHqlRepo(sessionFactory);
    private final Query<MusicGroup> query = Mockito.mock(Query.class);

    static Stream<Arguments> saveTestNominal() {
        return Stream.of(
                Arguments.arguments(new MusicGroup(null, "trolan", (short) 1950, (short) 2020)),
                Arguments.arguments(new MusicGroup(null, "jeid", (short) 1970, (short) 1950)),
                Arguments.arguments(new MusicGroup(null, "ufora", (short) 1990, null)),
                Arguments.arguments(new MusicGroup(null, "vurado", (short) 1980, null))
        );
    }

    @ParameterizedTest
    @MethodSource("saveTestNominal")
    void saveTest(MusicGroup musicGroup) {
        Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);

        cut.save(musicGroup);

        Mockito.verify(session, Mockito.times(1)).save(musicGroup);
    }

    static Stream<Arguments> findByNamesTestNominal() {
        Set<String> strings = new LinkedHashSet<>();
        strings.add("one");
        Set<String> strings1 = new LinkedHashSet<>();
        strings1.add("two");
        strings1.add("three");
        return Stream.of(
                Arguments.arguments(strings),
                Arguments.arguments(strings1),
                Arguments.arguments(strings),
                Arguments.arguments(strings1)
        );
    }

    @ParameterizedTest
    @MethodSource("findByNamesTestNominal")
    void findByNamesTest(Set<String> names) {
        Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
        Mockito.when(session.createQuery(SELECT_MUSIC_GROUP_BY_NAMES, MusicGroup.class)).thenReturn(query);

        cut.findByNames(names);

        Mockito.verify(query, Mockito.times(1)).setParameterList(NAME_PARAMETER, names);
        Mockito.verify(query, Mockito.times(1)).list();
    }

    static Stream<Arguments> findByNameTestNominal() {
        return Stream.of(
                Arguments.arguments(new MusicGroup(null, "trolan", (short) 1950, (short) 2020), "123"),
                Arguments.arguments(new MusicGroup(null, "jeid", (short) 1970, (short) 1950), "456"),
                Arguments.arguments(new MusicGroup(null, "ufora", (short) 1990, null), "asd"),
                Arguments.arguments(new MusicGroup(null, "vurado", (short) 1980, null), "asd")
        );
    }

    @ParameterizedTest
    @MethodSource("findByNameTestNominal")
    void findByNameTest(MusicGroup expected, String name) {
        Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
        Mockito.when(session.createQuery(SELECT_MUSIC_GROUP_BY_NAME, MusicGroup.class)).thenReturn(query);
        Mockito.when(query.uniqueResultOptional()).thenReturn(Optional.of(expected));

        cut.findByName(name);

        Mockito.verify(query, Mockito.times(1)).uniqueResultOptional();
    }
}