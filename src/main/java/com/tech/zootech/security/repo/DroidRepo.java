package com.tech.zootech.security.repo;

import com.tech.zootech.security.domain.Droid;
import com.tech.zootech.security.domain.Unicorn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DroidRepo extends JpaRepository<Droid, Long> {
    Optional<Droid> findDroidByUnicorn(Unicorn unicorn);
    Optional<List<Droid>> findDroidByAlive(Boolean alive);
    Optional<Set<Droid>> findDroidByName(String name);
}
