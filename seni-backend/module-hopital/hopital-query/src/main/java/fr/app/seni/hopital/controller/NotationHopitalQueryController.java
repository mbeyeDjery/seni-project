package fr.app.seni.hopital.controller;

import fr.app.seni.core.dto.ContratHopitalDto;
import fr.app.seni.core.dto.NotationHopitalDto;
import fr.app.seni.hopital.queryhandler.NotationHopitalHandlerService;
import fr.app.seni.hopital.service.NotationHopitalQueryService;
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
@RequestMapping(path = "/api/query/notation-hopital")
public class NotationHopitalQueryController {

    private  final NotationHopitalQueryService notationHopitalQueryService;

    @GetMapping("/{id}")
    public ResponseEntity<NotationHopitalDto> findOne(@PathVariable String id) {
        log.info("REQUEST to get NotationHopital by id");
        return ResponseEntity.ok(notationHopitalQueryService.findOne(id));
    }

    @GetMapping("/{idHopital}")
    public ResponseEntity<List<NotationHopitalDto>> findByHopital(@PathVariable String idHopital) {
        log.info("REQUEST to get NotationHopital by hopital");
        return ResponseEntity.ok(notationHopitalQueryService.getByHopital(idHopital));
    }
}
