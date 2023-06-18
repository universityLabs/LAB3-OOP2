package com.tech.zootech.security.domain.subdomain.service;

import com.tech.zootech.security.domain.subdomain.domain.Application;
import com.tech.zootech.security.domain.subdomain.repos.AdvisorRepository;
import com.tech.zootech.security.domain.subdomain.repos.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdvisorServiceImpl implements AdvisorService {
    private final AdvisorRepository advisorRepository;
    private final ApplicationRepository applicationRepository;

    @Override
    @Transactional
    public Application assignNewApplicationByAdvisorId(Long advisorId) {
        final var advisor = advisorRepository.findById(advisorId).orElseThrow();
        final var application = applicationRepository.findFirstByStatusOrderByCreatedAt(Application.Status.NEW);
        advisor.assignApplication(application);
        return application;
    }
}
