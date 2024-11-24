package fr.app.seni.parametre.service;

import fr.app.seni.core.dto.RegionDto;
import fr.app.seni.core.exception.CustomException;
import fr.app.seni.parametre.query.region.GetAllRegionQuery;
import fr.app.seni.parametre.query.region.GetRegionByIdQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegionQueryService {

    private final QueryGateway queryGateway;

    public RegionDto findOne(String id) {
        RegionDto regionDto = queryGateway.query(
                new GetRegionByIdQuery(id),
                ResponseTypes.instanceOf(RegionDto.class)
        ).join();

        if (regionDto == null){
            throw new CustomException("Aucun élément correspondant", HttpStatus.NOT_FOUND);
        }

        return regionDto;
    }

    public List<RegionDto> getAll() {
        return queryGateway.query(
                new GetAllRegionQuery(),
                ResponseTypes.multipleInstancesOf(RegionDto.class)
        ).join();
    }
}
