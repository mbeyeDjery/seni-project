package fr.app.seni.type.hopital.queryhandler;


import fr.app.seni.core.dto.TypeHopitalDto;
import fr.app.seni.core.service.TypeHopitalService;
import fr.app.seni.type.hopital.query.GetAllTypeHopitalQuery;
import fr.app.seni.type.hopital.query.GetTypeHopitalByEnabledQuery;
import fr.app.seni.type.hopital.query.GetTypeHopitalByIdQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TypeHopitalQueryHandler {

    private final TypeHopitalService typeHopitalService;

    @QueryHandler
    public List<TypeHopitalDto> onGetAll(GetAllTypeHopitalQuery query) {
        return typeHopitalService.findAll();
    }

    @QueryHandler
    public TypeHopitalDto onGetByID(GetTypeHopitalByIdQuery query) {
        return typeHopitalService.findOne(query.getIdTypeHopital());
    }

    @QueryHandler
    public List<TypeHopitalDto> onGetByEnabled(GetTypeHopitalByEnabledQuery query) {
        return typeHopitalService.findByEnabled(query.getEnabled());
    }
}
