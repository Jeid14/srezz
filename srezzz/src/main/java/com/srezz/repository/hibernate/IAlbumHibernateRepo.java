package com.srezz.repository.hibernate;

import com.srezz.entity.MusicGroup;
import java.util.Optional;

public interface IAlbumHibernateRepo {
    Optional<MusicGroup> findByName(String name);
}
