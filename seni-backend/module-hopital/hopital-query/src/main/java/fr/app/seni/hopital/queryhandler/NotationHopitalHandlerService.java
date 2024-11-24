package fr.app.seni.hopital.queryhandler;


import fr.app.seni.core.dto.NotationHopitalDto;
import fr.app.seni.core.service.NotationHopitalService;
import fr.app.seni.hopital.query.notation_hopital.GetNotationHopitalByIdQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotationHopitalHandlerService {

    private final NotationHopitalService notationHopitalService;

    @QueryHandler
    public NotationHopitalDto onGetByID(GetNotationHopitalByIdQuery query) {
        return notationHopitalService.findOne(query.getIdNotationHopital());
    }

    @QueryHandler
    public List<NotationHopitalDto> onGetByHopital(GetNotationHopitalByIdQuery query) {
        return notationHopitalService.findByHopital(query.getIdNotationHopital());
    }

}
