package fr.app.seni.hopital.controller;

import fr.app.seni.core.dto.HopitalDto;
import fr.app.seni.hopital.service.HopitalQueryService;
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
@RequestMapping(path = "/api/query/hopital")
public class HopitalQueryController {

    private  final HopitalQueryService hopitalQueryService;

    @GetMapping
    public ResponseEntity<List<HopitalDto>> getAll() {
        log.info("REQUEST to get all hopital");
        return ResponseEntity.ok(hopitalQueryService.getAll());
    }

    @GetMapping("/{idHopital}")
    public ResponseEntity<HopitalDto> findOne(@PathVariable String idHopital) {
        log.info("REQUEST to find one hopital : {}", idHopital);
        return ResponseEntity.ok(hopitalQueryService.findOne(idHopital));
    }
}
