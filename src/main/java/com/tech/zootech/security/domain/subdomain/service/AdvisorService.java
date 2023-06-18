package com.tech.zootech.security.domain.subdomain.service;

import com.tech.zootech.security.domain.subdomain.domain.Application;

public interface AdvisorService {
    Application assignNewApplicationByAdvisorId(Long advisorId);
}
