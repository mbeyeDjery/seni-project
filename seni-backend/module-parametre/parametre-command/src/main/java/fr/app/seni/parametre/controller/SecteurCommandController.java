package fr.app.seni.parametre.controller;

import fr.app.seni.core.cqrs.AggregateCreatedResponse;
import fr.app.seni.core.dto.SecteurDto;
import fr.app.seni.core.exception.CustomException;
import fr.app.seni.parametre.service.SecteurCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/manager/command/secteur")
public class SecteurCommandController {

    private final SecteurCommandService secteurCommandService;

    @PostMapping
    public ResponseEntity<AggregateCreatedResponse> create(@RequestBody SecteurDto secteur){
        log.info("REQUEST to create secteur : {}", secteur);

        if (secteur.getVille() == null){
            throw new CustomException("Aucune ville spécifiée", HttpStatus.BAD_REQUEST);
        }

        if (secteur.getLibelle().trim().isBlank()){
            throw new CustomException("Le nom est obligatoire", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(secteurCommandService.create(secteur));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody SecteurDto secteur){
        log.info("REQUEST to update secteur : {}", secteur);

        if (secteur.getVille() == null){
            throw new CustomException("Aucune ville spécifiée", HttpStatus.BAD_REQUEST);
        }

        if (secteur.getLibelle().trim().isBlank()){
            throw new CustomException("Le nom est obligatoire", HttpStatus.BAD_REQUEST);
        }

        secteurCommandService.update(secteur);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        log.info("REQUEST to delete secteur : {}", id);
        secteurCommandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
