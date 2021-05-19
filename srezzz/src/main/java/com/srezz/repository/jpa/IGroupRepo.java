package com.srezz.repository.jpa;

import com.srezz.entity.MusicGroup;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@ConditionalOnProperty(name = "db.connector", havingValue = "springData")
public interface IGroupRepo extends CrudRepository<MusicGroup, Long> {
    List<MusicGroup> findByNameInIgnoreCase(Set<String> names);
    Optional<MusicGroup> findByName(String oldName);
}