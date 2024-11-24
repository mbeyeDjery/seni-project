package fr.app.seni.hopital.controller;

import fr.app.seni.core.cqrs.AggregateCreatedResponse;
import fr.app.seni.core.dto.NotationHopitalDto;
import fr.app.seni.core.exception.CustomException;
import fr.app.seni.hopital.service.NotationHopitalCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/manager/command/notation-hopital")
public class NotationHopitalCommandController {

    private final NotationHopitalCommandService notationHopitalCommandService;

    @PostMapping
    public ResponseEntity<AggregateCreatedResponse> create(@RequestBody NotationHopitalDto notationHopital){
        log.info("REQUEST to create notationHopital : {}", notationHopital);

        if (notationHopital.getHopital() == null){
            throw new CustomException("Aucun hopital spécifier", HttpStatus.BAD_REQUEST);
        }

        if (notationHopital.getNote() == null || notationHopital.getNote().isNaN()){
            throw new CustomException("Aucune note spécifiée", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(notationHopitalCommandService.create(notationHopital));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody NotationHopitalDto notationHopital){
        log.info("REQUEST to update notationHopital : {}", notationHopital);
        if (notationHopital.getHopital() == null){
            throw new CustomException("Aucun hopital spécifier", HttpStatus.BAD_REQUEST);
        }

        if (notationHopital.getNote() == null || notationHopital.getNote().isNaN()){
            throw new CustomException("Aucune note spécifiée", HttpStatus.BAD_REQUEST);
        }
        notationHopitalCommandService.update(notationHopital);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        log.info("REQUEST to delete contrat : {}", id);
        notationHopitalCommandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
