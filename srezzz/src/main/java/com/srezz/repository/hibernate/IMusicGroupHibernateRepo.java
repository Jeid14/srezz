package com.srezz.repository.hibernate;

import com.srezz.entity.MusicGroup;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IMusicGroupHibernateRepo {
    void save(MusicGroup musicGroup);
    List<MusicGroup> findByNames(Set<String> names);
    Optional<MusicGroup> findByName(String oldName);
}
