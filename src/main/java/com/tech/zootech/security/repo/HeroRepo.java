package com.tech.zootech.security.repo;

import com.tech.zootech.security.domain.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HeroRepo extends JpaRepository<Hero, Long>, JpaSpecificationExecutor<Hero> {
    @Query(nativeQuery = true, value = "" +
            "SELECT * FROM heroes " +
            "WHERE city_name = :cityName " +
            "ORDER BY created DESC LIMIT 1;"
    )
    Optional<Hero> findLastCreatedHeroInCity(String cityName);
}
