package fr.app.seni.parametre.controller;

import fr.app.seni.core.dto.ProvinceDto;
import fr.app.seni.core.dto.RegionDto;
import fr.app.seni.core.dto.SecteurDto;
import fr.app.seni.core.dto.VilleDto;
import fr.app.seni.parametre.service.ProvinceQueryService;
import fr.app.seni.parametre.service.RegionQueryService;
import fr.app.seni.parametre.service.SecteurQueryService;
import fr.app.seni.parametre.service.VilleQueryService;
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
@RequestMapping(path = "/api/query/geo")
public class GeographieQueryController {

    private  final VilleQueryService villeQueryService;
    private  final RegionQueryService regionQueryService;
    private  final SecteurQueryService secteurQueryService;
    private  final ProvinceQueryService provinceQueryService;

    @GetMapping("/province")
    public ResponseEntity<List<ProvinceDto>> getAllProvince() {
        log.info("REQUEST to get all type ProvinceDto");
        return ResponseEntity.ok(provinceQueryService.getAll());
    }

    @GetMapping("/province/{id}")
    public ResponseEntity<ProvinceDto> findOneProvince(@PathVariable String id) {
        log.info("REQUEST to find one Province : {}", id);
        return ResponseEntity.ok(provinceQueryService.findOne(id));
    }

    @GetMapping("/province/region/{id}")
    public ResponseEntity<List<ProvinceDto>> getByProvince(@PathVariable String id) {
        log.info("REQUEST to get all type ProvinceDto");
        return ResponseEntity.ok(provinceQueryService.getByRegion(id));
    }

    @GetMapping("/region")
    public ResponseEntity<List<RegionDto>> getAllRegion() {
        log.info("REQUEST to get all type RegionDto");
        return ResponseEntity.ok(regionQueryService.getAll());
    }

    @GetMapping("/region/{id}")
    public ResponseEntity<RegionDto> findOneRegion(@PathVariable String id) {
        log.info("REQUEST to find one RegionDto : {}", id);
        return ResponseEntity.ok(regionQueryService.findOne(id));
    }

    @GetMapping("/secteur")
    public ResponseEntity<List<SecteurDto>> getAllSecteur() {
        log.info("REQUEST to get all type SecteurDto");
        return ResponseEntity.ok(secteurQueryService.getAll());
    }

    @GetMapping("/secteur/{id}")
    public ResponseEntity<SecteurDto> findOneSecteur(@PathVariable String id) {
        log.info("REQUEST to find one RegionDto : {}", id);
        return ResponseEntity.ok(secteurQueryService.findOne(id));
    }

    @GetMapping("/secteur/ville/{id}")
    public ResponseEntity<List<SecteurDto>> getSecteurByVille(@PathVariable String id) {
        log.info("REQUEST to get all type SecteurDto");
        return ResponseEntity.ok(secteurQueryService.getSecteurByVille(id));
    }

    @GetMapping("/ville")
    public ResponseEntity<List<VilleDto>> getAllVille() {
        log.info("REQUEST to get all type VilleDto");
        return ResponseEntity.ok(villeQueryService.getAll());
    }

    @GetMapping("/ville/{id}")
    public ResponseEntity<VilleDto> findOneVille(@PathVariable String id) {
        log.info("REQUEST to find one VilleDto : {}", id);
        return ResponseEntity.ok(villeQueryService.findOne(id));
    }

    @GetMapping("/ville/province/{id}")
    public ResponseEntity<List<VilleDto>> getVilleByProvince(@PathVariable String id) {
        log.info("REQUEST to get all type VilleDto");
        return ResponseEntity.ok(villeQueryService.getVilleByProvince(id));
    }
}
