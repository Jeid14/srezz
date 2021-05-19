package com.srezz.repository.hibernate.impl;

import com.srezz.entity.MusicGroup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MusicGroupHibernateJpqlTest {
    private final String queryForGetByNames = "SELECT mg FROM MusicGroup mg WHERE lower(name) IN (:name) AND mg.id in (SELECT a.musicGroup FROM Album a)";
    private final String queryForGetByName = "SELECT mg FROM MusicGroup mg WHERE lower(name) = lower(:name) AND mg.id in (SELECT a.musicGroup FROM Album a)";
    private final EntityManager entityManager = Mockito.mock(EntityManager.class);
    private final TypedQuery typedQuery = Mockito.mock(TypedQuery.class);
    private final MusicGroupHibernateJpql cut = new MusicGroupHibernateJpql(entityManager);

    static Stream<Arguments> validSource() {
        return Stream.of(
                Arguments.arguments(new MusicGroup(Long.valueOf("1"), "jeid", (short) 1, Short.valueOf("1880"))),
                Arguments.arguments(new MusicGroup(Long.valueOf("12"), "jeiddd", (short) 1590, Short.valueOf("1984"))),
                Arguments.arguments(new MusicGroup(Long.valueOf("48834"), "jeid11", (short) 123, Short.valueOf("1666")))
        );
    }

    @ParameterizedTest
    @MethodSource("validSource")
    void save(MusicGroup musicGroup) {
        Mockito.doNothing().when(entityManager).persist(musicGroup);
        cut.save(musicGroup);
        Mockito.verify(entityManager, Mockito.times(1)).persist(musicGroup);
    }

    @Test
    void findByNames() {
        Set<String> stringSet = new HashSet<>();
        stringSet.add("jeid");
        MusicGroup musicGroup = new MusicGroup(Long.valueOf("48834"), "jeid11", (short) 123, Short.valueOf("1666"));
        Mockito.when(entityManager.createQuery(queryForGetByNames, MusicGroup.class)).thenReturn(typedQuery);
        Mockito.when(typedQuery.setParameter("name", stringSet)).thenReturn(typedQuery);
        cut.findByNames(stringSet);
        Mockito.verify(typedQuery, Mockito.times(1)).getResultList();

    }

    @Test
    void findByName() {
        String oldName = "OLDLOGIN";
        MusicGroup musicGroup = new MusicGroup(Long.valueOf("48834"), "jeid11", (short) 123, Short.valueOf("1666"));
        Stream<MusicGroup> stream = Stream.of(musicGroup);

        Mockito.when(entityManager.createQuery(queryForGetByName, MusicGroup.class)).thenReturn(typedQuery);
        Mockito.when(typedQuery.getResultStream()).thenReturn(Stream.of(stream));

        cut.findByName(oldName);
        Mockito.verify(typedQuery, Mockito.times(1)).getResultStream();
    }
}