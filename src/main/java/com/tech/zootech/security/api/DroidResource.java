package com.tech.zootech.security.api;

import com.tech.zootech.security.DTO.DroidDto;
import com.tech.zootech.security.DTO.UnicornDto;
import com.tech.zootech.security.service.DroidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/droids/v1")
@RequiredArgsConstructor
public class DroidResource {
    private final DroidService droidService;

    @GetMapping("/droids-by-unicorn")
    ResponseEntity<DroidDto> getDroidByUnicorn(@RequestBody UnicornDto unicornDto) {
        return ResponseEntity.ok(droidService.getDroidByUnicorn(unicornDto));
    }

    @GetMapping("/droids-by-aliveness")
    ResponseEntity<List<DroidDto>> getDroidByAlive(@RequestParam Boolean alive) {
        return ResponseEntity.ok(droidService.getDroidByAlive(alive));
    }

    @GetMapping("/droids-by-name/{name}")
    ResponseEntity<Set<DroidDto>> getDroidByName(@PathVariable String name) {
        return ResponseEntity.ok(droidService.getDroidByName(name));
    }
}
