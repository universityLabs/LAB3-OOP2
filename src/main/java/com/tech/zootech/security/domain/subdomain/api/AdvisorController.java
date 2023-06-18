package com.tech.zootech.security.domain.subdomain.api;

import com.tech.zootech.security.domain.subdomain.domain.Application;
import com.tech.zootech.security.domain.subdomain.service.AdvisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/advisor/v1")
@RequiredArgsConstructor
public class AdvisorController {
    private final AdvisorService advisorService;

    @PostMapping("/{id}")
    public ResponseEntity<Application> assignNewApplicationByAdvisorId(@PathVariable Long id) {
        return ResponseEntity.ok(advisorService.assignNewApplicationByAdvisorId(id));
    }
}
