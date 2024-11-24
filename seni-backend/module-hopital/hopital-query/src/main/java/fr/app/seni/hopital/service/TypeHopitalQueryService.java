package fr.app.seni.hopital.service;

import fr.app.seni.core.dto.TypeHopitalDto;
import fr.app.seni.core.exception.CustomException;
import fr.app.seni.hopital.query.type_hopital.GetAllTypeHopitalQuery;
import fr.app.seni.hopital.query.type_hopital.GetTypeHopitalByEnabledQuery;
import fr.app.seni.hopital.query.type_hopital.GetTypeHopitalByIdQuery;
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
public class TypeHopitalQueryService {

    private final QueryGateway queryGateway;

    public List<TypeHopitalDto> getAll() {
        return queryGateway.query(
                new GetAllTypeHopitalQuery(),
                ResponseTypes.multipleInstancesOf(TypeHopitalDto.class)
        ).join();

    }

    public TypeHopitalDto findOne(String idTypeHopital) {
        TypeHopitalDto typeHopitalDto = queryGateway.query(
                new GetTypeHopitalByIdQuery(idTypeHopital),
                ResponseTypes.instanceOf(TypeHopitalDto.class)
        ).join();

        if (typeHopitalDto == null){
            throw new CustomException("Aucun élément correspondant", HttpStatus.NOT_FOUND);
        }

        return typeHopitalDto;
    }

    public List<TypeHopitalDto> findByEnabled(Boolean enabled) {
        return queryGateway.query(
                new GetTypeHopitalByEnabledQuery(enabled),
                ResponseTypes.multipleInstancesOf(TypeHopitalDto.class)
        ).join();
    }
}
