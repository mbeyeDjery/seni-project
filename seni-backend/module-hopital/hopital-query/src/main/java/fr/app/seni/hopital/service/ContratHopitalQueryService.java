package fr.app.seni.hopital.service;

import fr.app.seni.core.dto.ContratHopitalDto;
import fr.app.seni.core.enums.ContratHopitalStatus;
import fr.app.seni.core.exception.CustomException;
import fr.app.seni.hopital.query.contrat.GetContratByHopitalAndStatusQuery;
import fr.app.seni.hopital.query.contrat.GetContratByHopitalQuery;
import fr.app.seni.hopital.query.contrat.GetContratByIdQuery;
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
public class ContratHopitalQueryService {

    private final QueryGateway queryGateway;

    public ContratHopitalDto findOne(String idContratHopital) {
        ContratHopitalDto contratHopitalDto = queryGateway.query(
                new GetContratByIdQuery(idContratHopital),
                ResponseTypes.instanceOf(ContratHopitalDto.class)
        ).join();

        if (contratHopitalDto == null){
            throw new CustomException("Aucun élément correspondant", HttpStatus.NOT_FOUND);
        }

        return contratHopitalDto;
    }

    public List<ContratHopitalDto> getByHopital(String idHopital) {
        return queryGateway.query(
                new GetContratByHopitalQuery(idHopital),
                ResponseTypes.multipleInstancesOf(ContratHopitalDto.class)
        ).join();
    }

    public List<ContratHopitalDto> getByHopitalAndStatus(String idHopital, ContratHopitalStatus status) {
        return queryGateway.query(
                new GetContratByHopitalAndStatusQuery(idHopital, status),
                ResponseTypes.multipleInstancesOf(ContratHopitalDto.class)
        ).join();
    }
}
