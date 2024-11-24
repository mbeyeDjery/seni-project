package fr.app.seni.hopital.queryhandler;


import fr.app.seni.core.dto.ContratHopitalDto;
import fr.app.seni.core.service.ContratHopitalService;
import fr.app.seni.hopital.query.contrat.GetContratByHopitalAndStatusQuery;
import fr.app.seni.hopital.query.contrat.GetContratByHopitalQuery;
import fr.app.seni.hopital.query.contrat.GetContratByIdQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ContratHopitalHandlerService {

    private final ContratHopitalService contratHopitalService;

    @QueryHandler
    public ContratHopitalDto onGetByID(GetContratByIdQuery query) {
        return contratHopitalService.findOne(query.getIdContrattHopital());
    }

    @QueryHandler
    public List<ContratHopitalDto> onGetByHopital(GetContratByHopitalQuery query) {
        return contratHopitalService.findByHopital(query.getIdHopital());
    }

    @QueryHandler
    public List<ContratHopitalDto> onGetByHopitalAndStatus(GetContratByHopitalAndStatusQuery query) {
        return contratHopitalService.findByHopitalAndStatut(query.getIdHopital(), query.getStatut());
    }

}
