package fr.app.seni.parametre.service;

import fr.app.seni.core.dto.VilleDto;
import fr.app.seni.core.exception.CustomException;
import fr.app.seni.parametre.query.ville.GetAllVilleQuery;
import fr.app.seni.parametre.query.ville.GetVilleByProvinceQuery;
import fr.app.seni.parametre.query.ville.GetVillieByIdQuery;
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
public class VilleQueryService {

    private final QueryGateway queryGateway;

    public VilleDto findOne(String id) {
        VilleDto villeDto = queryGateway.query(
                new GetVillieByIdQuery(id),
                ResponseTypes.instanceOf(VilleDto.class)
        ).join();

        if (villeDto == null){
            throw new CustomException("Aucun élément correspondant", HttpStatus.NOT_FOUND);
        }

        return villeDto;
    }

    public List<VilleDto> getAll() {
        return queryGateway.query(
                new GetAllVilleQuery(),
                ResponseTypes.multipleInstancesOf(VilleDto.class)
        ).join();
    }

    public List<VilleDto> getVilleByProvince(String idProvince) {
        return queryGateway.query(
                new GetVilleByProvinceQuery(idProvince),
                ResponseTypes.multipleInstancesOf(VilleDto.class)
        ).join();
    }
}
