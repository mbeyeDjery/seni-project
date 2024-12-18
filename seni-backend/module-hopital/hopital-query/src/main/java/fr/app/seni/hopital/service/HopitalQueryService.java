package fr.app.seni.hopital.service;


import fr.app.seni.core.dto.HopitalDto;
import fr.app.seni.core.exception.CustomException;
import fr.app.seni.hopital.query.hopital.GetAllHopitalQuery;
import fr.app.seni.hopital.query.hopital.GetHopitalByIdQuery;
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
public class HopitalQueryService {

    private final QueryGateway queryGateway;

    public List<HopitalDto> getAll() {
        return queryGateway.query(
                new GetAllHopitalQuery(),
                ResponseTypes.multipleInstancesOf(HopitalDto.class)
        ).join();
    }

    public HopitalDto findOne(String idTypeHopital) {
        HopitalDto hopitalDto = queryGateway.query(
                new GetHopitalByIdQuery(idTypeHopital),
                ResponseTypes.instanceOf(HopitalDto.class)
        ).join();

        if (hopitalDto == null){
            throw new CustomException("Aucun élément correspondant", HttpStatus.NOT_FOUND);
        }

        return hopitalDto;
    }
}
