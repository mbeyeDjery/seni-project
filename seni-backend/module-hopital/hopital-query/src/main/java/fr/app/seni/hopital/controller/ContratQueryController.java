package fr.app.seni.hopital.controller;

import fr.app.seni.core.dto.*;
import fr.app.seni.core.enums.ContratHopitalStatus;
import fr.app.seni.hopital.service.*;
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
@RequestMapping(path = "/api/query/contrat-hopital")
public class ContratQueryController {

    private  final ContratHopitalQueryService contratHopitalQueryService;

    @GetMapping("/{idHopital}")
    public ResponseEntity<List<ContratHopitalDto>> getByHopital(@PathVariable String idHopital) {
        log.info("REQUEST to get ContratHopitalDto by hopital");
        return ResponseEntity.ok(contratHopitalQueryService.getByHopital(idHopital));
    }

    @GetMapping("/{idHopital}/{status}")
    public ResponseEntity<List<ContratHopitalDto>> getByHopitalAndStatus(@PathVariable String idHopital, @PathVariable ContratHopitalStatus status) {
        log.info("REQUEST to get all ContratHopital by hopital and status");
        return ResponseEntity.ok(contratHopitalQueryService.getByHopitalAndStatus(idHopital, status));
    }
}
