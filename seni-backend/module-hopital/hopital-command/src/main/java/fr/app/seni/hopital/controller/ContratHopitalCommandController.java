package fr.app.seni.hopital.controller;

import fr.app.seni.core.cqrs.AggregateCreatedResponse;
import fr.app.seni.core.dto.ContratHopitalDto;
import fr.app.seni.core.dto.HopitalStatutRequest;
import fr.app.seni.core.dto.request.ContratStatutRequest;
import fr.app.seni.core.enums.ContratHopitalStatus;
import fr.app.seni.core.exception.CustomException;
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
        if (contratHopital.getHopital() == null ){
            throw new CustomException("Aucun hopital spécifier", HttpStatus.BAD_REQUEST);
        }
         if (contratHopital.getReference().isBlank()){
             throw new CustomException("La référence du contrat est obligatoire", HttpStatus.BAD_REQUEST);
         }

         if (contratHopital.getDateDebut() == null || contratHopital.getDateFin() == null){
             throw new CustomException("La date de ébut et de fin sont obligatoire", HttpStatus.BAD_REQUEST);
         }
        return ResponseEntity.status(HttpStatus.CREATED).body(contratHopitalCommandService.create(contratHopital));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ContratHopitalDto contratHopital){
        log.info("REQUEST to update contratHopital : {}", contratHopital);
        if (contratHopital.getHopital() == null ){
            throw new CustomException("Aucun hopital spécifier", HttpStatus.BAD_REQUEST);
        }
        if (contratHopital.getReference().isBlank()){
            throw new CustomException("La référence du contrat est obligatoire", HttpStatus.BAD_REQUEST);
        }

        if (contratHopital.getDateDebut() == null || contratHopital.getDateFin() == null){
            throw new CustomException("La date de début et de fin sont obligatoire", HttpStatus.BAD_REQUEST);
        }

        if (contratHopital.getDateDebut().equals(contratHopital.getDateFin()) || contratHopital.getDateDebut().isAfter(contratHopital.getDateFin())) {
            throw new CustomException("La date de début doit être anterieur à la date de fin", HttpStatus.BAD_REQUEST);
        }

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

        if (contratStatutRequest.idContrattHopital().isBlank()){
            throw new CustomException("Le contrat n'est pas specifié", HttpStatus.BAD_REQUEST);
        }

        if (contratStatutRequest.statut() == null){
            throw new CustomException("Le statut n'est pas defini", HttpStatus.BAD_REQUEST);
        }

        if (contratStatutRequest.motif().isBlank()){
            throw new CustomException("Le motif doit être spécifié", HttpStatus.BAD_REQUEST);
        }

        contratHopitalCommandService.changeStatus(contratStatutRequest);
        return ResponseEntity.noContent().build();
    }
}
