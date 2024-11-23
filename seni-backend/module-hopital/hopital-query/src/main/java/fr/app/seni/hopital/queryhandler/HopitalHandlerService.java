package fr.app.seni.hopital.queryhandler;


import fr.app.seni.core.dto.HopitalDto;
import fr.app.seni.core.service.HopitalService;
import fr.app.seni.hopital.query.GetAllHopitalQuery;
import fr.app.seni.hopital.query.GetHopitalByIdQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class HopitalHandlerService {

    private final HopitalService hopitalService;

    @QueryHandler
    public List<HopitalDto> onGetAll(GetAllHopitalQuery query) {
        return hopitalService.findAll();
    }

    @QueryHandler
    public HopitalDto onGetByID(GetHopitalByIdQuery query) {
        return hopitalService.findOne(query.getIdHopital());
    }
}
