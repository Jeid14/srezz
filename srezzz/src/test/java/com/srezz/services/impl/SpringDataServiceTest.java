package com.srezz.services.impl;

import com.srezz.entity.MusicGroup;
import com.srezz.exception.GroupNotFoundException;
import com.srezz.modelDto.MusicGroupSaveDto;
import com.srezz.modelDto.MusicGroupUpdateDto;
import com.srezz.repository.jpa.IGroupRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

class SpringDataServiceTest {
    private final IGroupRepo iGroupRepo = Mockito.mock(IGroupRepo.class);
    private final SpringDataService cut = new SpringDataService(iGroupRepo);

    private static Stream<Arguments> findByNamesTestSource() {
        Set<String> stringSet1 = new HashSet<>();
        stringSet1.add("qwerty");
        Set<String> stringSet2 = new HashSet<>();
        stringSet2.add("hollywood undead");
        stringSet2.add("kikaqwe");

        return Stream.of(
                Arguments.arguments(stringSet1),
                Arguments.arguments(stringSet2)
        );
    }

    private static Stream<Arguments> saveTestNominal() {
        return Stream.of(
                Arguments.arguments(new MusicGroup(null, "Смех", (short) 1999, null)),
                Arguments.arguments(new MusicGroup(null, "Hollywood Undead", (short) 2005, null)),
                Arguments.arguments(new MusicGroup(null, "t.A.T.u.", (short) 1999, (short) 2016))
        );
    }

    public static Stream<Arguments> updateMusicGroupTestNominal() {
        return Stream.of(
                Arguments.arguments(new MusicGroupUpdateDto("oldName", "newName", (short) 2002, (short) 2020)),
                Arguments.arguments(new MusicGroupUpdateDto("jeid", "qwe", (short) 2012, (short) 2002)),
                Arguments.arguments(new MusicGroupUpdateDto("vurado", "trolan", (short) 2012, (short) 2020)),
                Arguments.arguments(new MusicGroupUpdateDto("realtime", "something", (short) 2012, (short) 2002)
                ));
    }

    public static Stream<Arguments> saveMusicGroupTestNominal() {
        return Stream.of(
                Arguments.arguments(new MusicGroupSaveDto("oldName", (short) 2010, (short) 2020)),
                Arguments.arguments(new MusicGroupSaveDto("jeid", (short) 2012, (short) 2040)),
                Arguments.arguments(new MusicGroupSaveDto("vurado", (short) 2012, (short) 2020)),
                Arguments.arguments(new MusicGroupSaveDto("realtime", (short) 2012, (short) 2014)
                ));
    }


    @ParameterizedTest
    @MethodSource("updateMusicGroupTestNominal")
    void editMusicGroupTest(MusicGroupUpdateDto musicGroupUpdateDto) {
        MusicGroup musicGroup = new MusicGroup(null, musicGroupUpdateDto.getNewName(), musicGroupUpdateDto.getCreationYear(), musicGroupUpdateDto.getDecayYear());
        Mockito.when(iGroupRepo.findByName(musicGroupUpdateDto.getOldName())).thenReturn(Optional.of(musicGroup));
        cut.editMusicGroup(musicGroupUpdateDto);
        Mockito.verify(iGroupRepo, Mockito.times(1)).save(musicGroup);
    }


    @ParameterizedTest
    @MethodSource("saveMusicGroupTestNominal")
    void addMusicGroupTest(MusicGroupSaveDto musicGroupSaveDto) {
        MusicGroup musicGroup = new MusicGroup(null, musicGroupSaveDto.getName(), musicGroupSaveDto.getCreationYear(), musicGroupSaveDto.getDecayYear());
        cut.addMusicGroup(musicGroupSaveDto);
        Mockito.verify(iGroupRepo, Mockito.times(1)).save(musicGroup);
    }

    @ParameterizedTest
    @MethodSource("findByNamesTestSource")
    void getMusicGroupsTest(Set<String> names) {
        Mockito.when(iGroupRepo.findByNameInIgnoreCase(names)).thenReturn(new ArrayList<>());
        cut.getMusicGroups(names);
        Mockito.verify(iGroupRepo, Mockito.times(1)).findByNameInIgnoreCase(names);
    }

    @Test
    void editMusicGroupTestException() {
        MusicGroupUpdateDto musicGroupUpdateDto = new MusicGroupUpdateDto("oldName", "newName", (short) 2002, (short) 2020);
        Mockito.when(iGroupRepo.findByName(musicGroupUpdateDto.getOldName())).thenThrow(GroupNotFoundException.class);
        Assertions.assertThrows(GroupNotFoundException.class, () -> cut.editMusicGroup(musicGroupUpdateDto));
    }
}