package fr.app.seni.parametre.service;

import fr.app.seni.core.dto.ProvinceDto;
import fr.app.seni.core.exception.CustomException;
import fr.app.seni.parametre.query.province.GetAllProvinceQuery;
import fr.app.seni.parametre.query.province.GetProvinceByIdQuery;
import fr.app.seni.parametre.query.province.GetProvinceByRegionQuery;
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
public class ProvinceQueryService {

    private final QueryGateway queryGateway;

    public ProvinceDto findOne(String id) {
        ProvinceDto provinceDto = queryGateway.query(
                new GetProvinceByIdQuery(id),
                ResponseTypes.instanceOf(ProvinceDto.class)
        ).join();

        if (provinceDto == null){
            throw new CustomException("Aucun élément correspondant", HttpStatus.NOT_FOUND);
        }
        return provinceDto;
    }

    public List<ProvinceDto> getAll() {
        return queryGateway.query(
                new GetAllProvinceQuery(),
                ResponseTypes.multipleInstancesOf(ProvinceDto.class)
        ).join();
    }

    public List<ProvinceDto> getByRegion(String idRegion) {
        return queryGateway.query(
                new GetProvinceByRegionQuery(idRegion),
                ResponseTypes.multipleInstancesOf(ProvinceDto.class)
        ).join();
    }
}
