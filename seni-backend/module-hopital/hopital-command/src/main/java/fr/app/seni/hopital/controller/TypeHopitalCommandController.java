package fr.app.seni.hopital.controller;

import fr.app.seni.core.cqrs.AggregateCreatedResponse;
import fr.app.seni.core.dto.TypeHopitalDto;
import fr.app.seni.hopital.service.TypeHopitalCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/manager/command/type-hopital")
public class TypeHopitalCommandController {

    private final TypeHopitalCommandService typeHopitalCommandService;

    @PostMapping
    public ResponseEntity<AggregateCreatedResponse> create(@RequestBody TypeHopitalDto typeHopitalDto){
        log.info("REQUEST to create type hopital : {}", typeHopitalDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(typeHopitalCommandService.create(typeHopitalDto));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody TypeHopitalDto typeHopitalDto){
        log.info("REQUEST to update type hopital : {}", typeHopitalDto);
        typeHopitalCommandService.update(typeHopitalDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idTypeHopital}")
    public ResponseEntity<Void> delete(@PathVariable String idTypeHopital){
        log.info("REQUEST to delete type hopital : {}", idTypeHopital);
        typeHopitalCommandService.delete(idTypeHopital);
        return ResponseEntity.noContent().build();
    }
}
