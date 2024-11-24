package fr.app.seni.parametre.queryhandler;


import fr.app.seni.core.dto.VilleDto;
import fr.app.seni.core.service.VilleService;
import fr.app.seni.parametre.query.ville.GetAllVilleQuery;
import fr.app.seni.parametre.query.ville.GetVilleByProvinceQuery;
import fr.app.seni.parametre.query.ville.GetVillieByIdQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class VilleHandlerService {

    private final VilleService villeService;

    @QueryHandler
    public VilleDto onGetByID(GetVillieByIdQuery query) {
        return villeService.findOne(query.getId());
    }

    @QueryHandler
    public List<VilleDto> onGetAll(GetAllVilleQuery query) {
        return villeService.findAll();
    }

    @QueryHandler
    public List<VilleDto> onGetByProvince(GetVilleByProvinceQuery query) {
        return villeService.findByProvince(query.getIdProvince());
    }

}
