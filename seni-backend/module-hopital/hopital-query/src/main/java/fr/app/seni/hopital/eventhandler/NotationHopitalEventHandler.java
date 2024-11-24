package fr.app.seni.hopital.eventhandler;


import fr.app.seni.core.cqrs.notation_hopital.event.NotationHopitalCreatedEvent;
import fr.app.seni.core.cqrs.notation_hopital.event.NotationHopitalDeletedEvent;
import fr.app.seni.core.cqrs.notation_hopital.event.NotationHopitalUpdatedEvent;
import fr.app.seni.core.service.NotationHopitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotationHopitalEventHandler {

    private final NotationHopitalService notationHopitalService;

    @EventHandler
    @Transactional
    protected void onCreatedEvent(NotationHopitalCreatedEvent event) {
        event.getNotationHopital().setIdNotation(event.getId());
        notationHopitalService.create(event.getNotationHopital());
    }

    @EventHandler
    @Transactional
    protected void onUpdatedEvent(NotationHopitalUpdatedEvent event) {
        notationHopitalService.update(event.getNotationHopital());
    }

    @EventHandler
    @Transactional
    protected void onDeletedEvent(NotationHopitalDeletedEvent event) {
        notationHopitalService.delete(notationHopitalService.findOne(event.getId()));
    }
}
