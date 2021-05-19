package com.srezz.repository.hibernate;

import com.srezz.entity.Genre;
import java.util.Optional;

public interface IGenreHibernateRepo {
    Optional<Genre> findByName(String name);
}
