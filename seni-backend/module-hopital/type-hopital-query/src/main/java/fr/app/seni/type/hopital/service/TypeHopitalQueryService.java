package fr.app.seni.type.hopital.service;

import fr.app.seni.core.dto.TypeHopitalDto;
import fr.app.seni.type.hopital.query.GetAllTypeHopitalQuery;
import fr.app.seni.type.hopital.query.GetTypeHopitalByEnabledQuery;
import fr.app.seni.type.hopital.query.GetTypeHopitalByIdQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
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
        return queryGateway.query(
                new GetTypeHopitalByIdQuery(idTypeHopital),
                ResponseTypes.instanceOf(TypeHopitalDto.class)
        ).join();
    }

    public List<TypeHopitalDto> findByEnabled(Boolean enabled) {
        return queryGateway.query(
                new GetTypeHopitalByEnabledQuery(enabled),
                ResponseTypes.multipleInstancesOf(TypeHopitalDto.class)
        ).join();
    }
}
