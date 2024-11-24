package fr.app.seni.hopital.controller;

import fr.app.seni.core.cqrs.AggregateCreatedResponse;
import fr.app.seni.core.dto.ProvinceDto;
import fr.app.seni.core.exception.CustomException;
import fr.app.seni.hopital.service.ProvinceCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/manager/command/province")
public class ProvinceCommandController {

    private final ProvinceCommandService provinceCommandService;

    @PostMapping
    public ResponseEntity<AggregateCreatedResponse> create(@RequestBody ProvinceDto province){
        log.info("REQUEST to create province : {}", province);

        if (province.getRegion() == null){
            throw new CustomException("Aucune région spécifiée", HttpStatus.BAD_REQUEST);
        }

        if (province.getNom().trim().isBlank()){
            throw new CustomException("Le nom est obligatoire", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(provinceCommandService.create(province));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ProvinceDto province){
        log.info("REQUEST to update province : {}", province);

        if (province.getRegion() == null){
            throw new CustomException("Aucune région spécifiée", HttpStatus.BAD_REQUEST);
        }

        if (province.getNom().trim().isBlank()){
            throw new CustomException("Le nom est obligatoire", HttpStatus.BAD_REQUEST);
        }

        provinceCommandService.update(province);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        log.info("REQUEST to delete province : {}", id);
        provinceCommandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
