package fr.app.seni.type.hopital.controller;

import fr.app.seni.core.dto.TypeHopitalDto;
import fr.app.seni.type.hopital.service.TypeHopitalQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/query/type-hopital")
public class TypeHopitalQueryController {

    private  final TypeHopitalQueryService typeHopitalQueryService;

    @GetMapping
    public ResponseEntity<List<TypeHopitalDto>> getAll() {
        log.info("REQUEST to get all type hopital");
        return ResponseEntity.ok(typeHopitalQueryService.getAll());
    }

    @GetMapping("/{idTypeHopital}")
    public ResponseEntity<TypeHopitalDto> findOne(@PathVariable String idTypeHopital) {
        log.info("REQUEST to find one type hopital : {}", idTypeHopital);
        return ResponseEntity.ok(typeHopitalQueryService.findOne(idTypeHopital));
    }

    @GetMapping("/enabled/{enabled}")
    public ResponseEntity<List<TypeHopitalDto>> findByEnabled(@PathVariable Boolean enabled) {
        log.info("REQUEST to find type hopital by enabled : {}", enabled);
        return ResponseEntity.ok(typeHopitalQueryService.findByEnabled(enabled));
    }
}
