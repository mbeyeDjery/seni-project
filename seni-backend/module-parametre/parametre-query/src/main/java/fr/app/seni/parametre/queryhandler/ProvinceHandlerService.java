package fr.app.seni.parametre.queryhandler;


import fr.app.seni.core.dto.ProvinceDto;
import fr.app.seni.core.service.ProvinceService;
import fr.app.seni.parametre.query.province.GetAllProvinceQuery;
import fr.app.seni.parametre.query.province.GetProvinceByIdQuery;
import fr.app.seni.parametre.query.province.GetProvinceByRegionQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProvinceHandlerService {

    private final ProvinceService provinceService;

    @QueryHandler
    public ProvinceDto onGetByID(GetProvinceByIdQuery query) {
        return provinceService.findOne(query.getId());
    }

    @QueryHandler
    public List<ProvinceDto> onGetAll(GetAllProvinceQuery query) {
        return provinceService.findAll();
    }

    @QueryHandler
    public List<ProvinceDto> onGetByRegion(GetProvinceByRegionQuery query) {
        return provinceService.findByRegion(query.getIdRegion());
    }

}
