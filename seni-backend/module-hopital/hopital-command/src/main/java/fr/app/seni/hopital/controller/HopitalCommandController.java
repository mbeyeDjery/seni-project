package fr.app.seni.hopital.controller;

import fr.app.seni.core.cqrs.AggregateCreatedResponse;
import fr.app.seni.core.dto.HopitalDto;
import fr.app.seni.core.exception.CustomException;
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
}
