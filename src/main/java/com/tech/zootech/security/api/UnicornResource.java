package com.tech.zootech.security.api;

import com.tech.zootech.security.DTO.UnicornDto;
import com.tech.zootech.security.domain.enums.Color;
import com.tech.zootech.security.service.UnicornService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/unicorn/v1")
@RequiredArgsConstructor
public class UnicornResource {
    private final UnicornService unicornService;

    @GetMapping
    public ResponseEntity<UnicornDto> get(@RequestBody UnicornDto unicornDto) {
        return ResponseEntity.ok().body(unicornService.save(unicornDto));
    }

    @PostMapping
    public ResponseEntity<UnicornDto> save(@RequestBody UnicornDto unicornDto) {
        return ResponseEntity.ok().body(unicornService.save(unicornDto));
    }

    @GetMapping("/info/{name}")
    public ResponseEntity<UnicornDto> getByName(@PathVariable(name = "name") String name) {
        return ResponseEntity.ok().body(unicornService.getByName(name));
    }

    @GetMapping("/get-by-color")
    public ResponseEntity<List<UnicornDto>> getByColor(@RequestParam Color color) {
        return ResponseEntity.ok().body(unicornService.getByColor(color));
    }
}
