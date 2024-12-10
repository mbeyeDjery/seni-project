package fr.app.seni.hopital.controller;

import fr.app.seni.core.cqrs.AggregateCreatedResponse;
import fr.app.seni.core.dto.RegionDto;
import fr.app.seni.hopital.service.RegionCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/manager/command/region")
public class RegionCommandController {

    private final RegionCommandService regionCommandService;

    @PostMapping
    public ResponseEntity<AggregateCreatedResponse> create(@RequestBody RegionDto region){
        log.info("REQUEST to create region : {}", region);
        return ResponseEntity.status(HttpStatus.CREATED).body(regionCommandService.create(region));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody RegionDto region){
        log.info("REQUEST to update region : {}", region);
        regionCommandService.update(region);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        log.info("REQUEST to delete region : {}", id);
        regionCommandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
