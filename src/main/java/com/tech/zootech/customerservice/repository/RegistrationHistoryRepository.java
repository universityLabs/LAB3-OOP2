package com.tech.zootech.customerservice.repository;

import com.tech.zootech.customerservice.domain.entity.RegistrationHistory;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistrationHistoryRepository extends JpaRepository<RegistrationHistory, Long> {
    @Query("select rh.status , count(rh) from RegistrationHistory rh group by rh.status")
    List<Tuple> groupByStatus();
}
