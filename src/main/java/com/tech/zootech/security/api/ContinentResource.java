package com.tech.zootech.security.api;

import com.tech.zootech.security.DTO.ContinentDto;
import com.tech.zootech.security.DTO.PlanetDto;
import com.tech.zootech.security.service.ContinentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/continents/v1")
@RequiredArgsConstructor
public class ContinentResource {
    private final ContinentService continentService;

    @PostMapping
    public ResponseEntity<ContinentDto> saveContinent(@RequestBody ContinentDto continentDto) {
        return ResponseEntity.ok().body(continentService.save(continentDto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteContinent(@RequestBody ContinentDto continentDto) {
        continentService.delete(continentDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search-by-planet")
    public ResponseEntity<List<ContinentDto>> getContinentsByPlanet(@RequestParam("planet") PlanetDto planetDto) {
        return ResponseEntity.ok().body(continentService.getByPlanet(planetDto));
    }

    @GetMapping("/continent/{continent}")
    public ResponseEntity<ContinentDto> getContinentByName(@PathVariable("continent") String name) {
        return ResponseEntity.ok().body(continentService.getByName(name));
    }
}
