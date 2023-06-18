package com.tech.zootech.security.domain.subdomain.repos;

import com.tech.zootech.security.domain.subdomain.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Application findFirstByStatusOrderByCreatedAt(Application.Status status);
}
