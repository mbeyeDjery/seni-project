package fr.app.seni.hopital.controller;

import fr.app.seni.core.cqrs.AggregateCreatedResponse;
import fr.app.seni.core.dto.ContratHopitalDto;
import fr.app.seni.core.dto.request.ContratStatutRequest;
import fr.app.seni.hopital.service.ContratHopitalCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/manager/command/contrat-hopital")
public class ContratHopitalCommandController {

    private final ContratHopitalCommandService contratHopitalCommandService;

    @PostMapping
    public ResponseEntity<AggregateCreatedResponse> create(@RequestBody ContratHopitalDto contratHopital){
        log.info("REQUEST to create contratHopital : {}", contratHopital);
        return ResponseEntity.status(HttpStatus.CREATED).body(contratHopitalCommandService.create(contratHopital));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ContratHopitalDto contratHopital){
        log.info("REQUEST to update contratHopital : {}", contratHopital);
        contratHopitalCommandService.update(contratHopital);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        log.info("REQUEST to delete contratHopital : {}", id);
        contratHopitalCommandService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/statut")
    public ResponseEntity<Void> changeStatut(@RequestBody ContratStatutRequest contratStatutRequest){
        log.info("REQUEST to update contrat hopital status : {}", contratStatutRequest);
        contratHopitalCommandService.changeStatus(contratStatutRequest);
        return ResponseEntity.noContent().build();
    }
}
