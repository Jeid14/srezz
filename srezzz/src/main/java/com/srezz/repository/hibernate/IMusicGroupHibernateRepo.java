package com.srezz.repository.hibernate;

import com.srezz.entity.Album;
import com.srezz.entity.MusicGroup;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IMusicGroupHibernateRepo {
    void save(String name, short yearRelease, Set<Album> albums);
    boolean existsByNameAndGroup(String name, MusicGroup group);
    Optional<MusicGroup> findByNameAndAlbum(String name, Album album);
    List<MusicGroup> findByNames(Set<String> names);
}
