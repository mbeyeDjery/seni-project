package fr.app.seni.parametre.controller;

import fr.app.seni.core.cqrs.AggregateCreatedResponse;
import fr.app.seni.core.dto.VilleDto;
import fr.app.seni.core.exception.CustomException;
import fr.app.seni.parametre.service.VilleCommandService;
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

        if (ville.getProvince() == null){
            throw new CustomException("Aucune province spécifiée", HttpStatus.BAD_REQUEST);
        }

        if (ville.getNom().trim().isBlank()){
            throw new CustomException("Le nom est obligatoire", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(villeCommandService.create(ville));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody VilleDto ville){
        log.info("REQUEST to update ville : {}", ville);

        if (ville.getProvince() == null){
            throw new CustomException("Aucune province spécifiée", HttpStatus.BAD_REQUEST);
        }

        if (ville.getNom().trim().isBlank()){
            throw new CustomException("Le nom est obligatoire", HttpStatus.BAD_REQUEST);
        }

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
