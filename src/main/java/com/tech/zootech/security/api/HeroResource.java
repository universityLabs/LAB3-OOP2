package com.tech.zootech.security.api;

import com.tech.zootech.security.DTO.HeroDto;
import com.tech.zootech.security.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("heroes/v1")
@RequiredArgsConstructor
public class HeroResource {
    private final HeroService heroService;

    @GetMapping("/last/{city}")
    public ResponseEntity<HeroDto> getLastHero(@PathVariable("city") String city) {
        return ResponseEntity.ok(heroService.getLastCreated(city));
    }
}
