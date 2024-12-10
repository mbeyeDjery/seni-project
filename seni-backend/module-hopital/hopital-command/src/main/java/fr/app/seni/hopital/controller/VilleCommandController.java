package fr.app.seni.hopital.controller;

import fr.app.seni.core.cqrs.AggregateCreatedResponse;
import fr.app.seni.core.dto.VilleDto;
import fr.app.seni.hopital.service.VilleCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/manager/command/ville")
public class VilleCommandController {

    private final VilleCommandService villeCommandService;

    @PostMapping
    public ResponseEntity<AggregateCreatedResponse> create(@RequestBody VilleDto ville){
        log.info("REQUEST to create ville : {}", ville);
        return ResponseEntity.status(HttpStatus.CREATED).body(villeCommandService.create(ville));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody VilleDto ville){
        log.info("REQUEST to update ville : {}", ville);
        villeCommandService.update(ville);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        log.info("REQUEST to delete ville : {}", id);
        villeCommandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
