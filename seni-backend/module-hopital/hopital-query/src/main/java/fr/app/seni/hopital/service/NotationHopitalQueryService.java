package fr.app.seni.hopital.service;

import fr.app.seni.core.dto.NotationHopitalDto;
import fr.app.seni.core.exception.CustomException;
import fr.app.seni.hopital.query.notation_hopital.GetNotationByHopitalQuery;
import fr.app.seni.hopital.query.notation_hopital.GetNotationHopitalByIdQuery;
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
public class NotationHopitalQueryService {

    private final QueryGateway queryGateway;

    public NotationHopitalDto findOne(String id) {
        NotationHopitalDto notationHopitalDto = queryGateway.query(
                new GetNotationHopitalByIdQuery(id),
                ResponseTypes.instanceOf(NotationHopitalDto.class)
        ).join();

        if (notationHopitalDto == null){
            throw new CustomException("Aucun élément correspondant", HttpStatus.NOT_FOUND);
        }

        return notationHopitalDto;
    }

    public List<NotationHopitalDto> getByHopital(String idHopital) {
        return queryGateway.query(
                new GetNotationByHopitalQuery(idHopital),
                ResponseTypes.multipleInstancesOf(NotationHopitalDto.class)
        ).join();
    }
}
