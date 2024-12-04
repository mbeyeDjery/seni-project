package fr.app.seni.hopital.controller;

import fr.app.seni.core.cqrs.AggregateCreatedResponse;
import fr.app.seni.core.dto.HopitalDto;
import fr.app.seni.core.dto.HopitalStatutRequest;
import fr.app.seni.core.exception.CustomException;
import fr.app.seni.hopital.service.GenerateCodeService;
import fr.app.seni.hopital.service.HopitalCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/manager/command/hopital")
public class HopitalCommandController {

    private final GenerateCodeService generateCodeService;
    private final HopitalCommandService hopitalCommandService;

    @PostMapping
    public ResponseEntity<AggregateCreatedResponse> create(@RequestBody HopitalDto hopitalDto){
        log.info("REQUEST to create hopital : {}", hopitalDto);

        if (hopitalDto.getNom().trim().isBlank()){
            throw new CustomException("Le nom est obligatoire", HttpStatus.BAD_REQUEST);
        }

        if (hopitalDto.getTelephone().trim().isBlank()){
            throw new CustomException("Le téléphone est obligatoire", HttpStatus.BAD_REQUEST);
        }

        hopitalDto.setCodeHopital(generateCodeService.getHopitalCode());
        return ResponseEntity.status(HttpStatus.CREATED).body(hopitalCommandService.create(hopitalDto));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody HopitalDto hopitalDto){
        log.info("REQUEST to update hopital : {}", hopitalDto);

        if (hopitalDto.getNom().trim().isBlank()){
            throw new CustomException("Le nom est obligatoire", HttpStatus.BAD_REQUEST);
        }

        if (hopitalDto.getTelephone().trim().isBlank()){
            throw new CustomException("Le téléphone est obligatoire", HttpStatus.BAD_REQUEST);
        }

        hopitalCommandService.update(hopitalDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idHopital}")
    public ResponseEntity<Void> delete(@PathVariable String idHopital){
        log.info("REQUEST to delete hopital : {}", idHopital);
        hopitalCommandService.delete(idHopital);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/statut")
    public ResponseEntity<Void> changeStatut(@RequestBody HopitalStatutRequest hopitalStatutRequest){
        log.info("REQUEST to update hopital status : {}", hopitalStatutRequest);

        if (hopitalStatutRequest.idHopital().isBlank()){
            throw new CustomException("Hopital non specifié", HttpStatus.BAD_REQUEST);
        }

        if (hopitalStatutRequest.statut() == null){
            throw new CustomException("Le statut n'est pas defini", HttpStatus.BAD_REQUEST);
        }

        if (hopitalStatutRequest.motif().isBlank()){
            throw new CustomException("Le motif doit être spécifié", HttpStatus.BAD_REQUEST);
        }

        hopitalCommandService.changeStatus(hopitalStatutRequest);
        return ResponseEntity.noContent().build();
    }
}
