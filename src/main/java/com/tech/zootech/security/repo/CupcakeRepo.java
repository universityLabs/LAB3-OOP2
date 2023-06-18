package com.tech.zootech.security.repo;

import com.tech.zootech.security.domain.Cupcake;
import com.tech.zootech.security.domain.Droid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CupcakeRepo extends JpaRepository<Cupcake, Long> {
    Optional<List<Cupcake>> findByDroid(Droid droid);
}
