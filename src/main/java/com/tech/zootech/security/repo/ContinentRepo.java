package com.tech.zootech.security.repo;

import com.tech.zootech.security.domain.Continent;
import com.tech.zootech.security.domain.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContinentRepo extends JpaRepository<Continent, Long> {
    Optional<List<Continent>> findByPlanet(Planet planet);
    Optional<Continent> findByName(String continentName);
}
