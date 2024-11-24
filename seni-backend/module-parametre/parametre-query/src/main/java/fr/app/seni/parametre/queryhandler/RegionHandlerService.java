package fr.app.seni.parametre.queryhandler;


import fr.app.seni.core.dto.RegionDto;
import fr.app.seni.core.service.RegionService;
import fr.app.seni.parametre.query.region.GetAllRegionQuery;
import fr.app.seni.parametre.query.region.GetRegionByIdQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegionHandlerService {

    private final RegionService regionService;

    @QueryHandler
    public RegionDto onGetByID(GetRegionByIdQuery query) {
        return regionService.findOne(query.getId());
    }

    @QueryHandler
    public List<RegionDto> onGetAll(GetAllRegionQuery query) {
        return regionService.findAll();
    }

}
