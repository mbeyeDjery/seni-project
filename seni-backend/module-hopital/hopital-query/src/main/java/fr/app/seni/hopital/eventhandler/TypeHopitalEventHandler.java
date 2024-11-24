package fr.app.seni.hopital.eventhandler;


import fr.app.seni.core.cqrs.type_hopital.event.TypeHopitalCreatedEvent;
import fr.app.seni.core.cqrs.type_hopital.event.TypeHopitalDeletedEvent;
import fr.app.seni.core.cqrs.type_hopital.event.TypeHopitalUpdatedEvent;
import fr.app.seni.core.service.TypeHopitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TypeHopitalEventHandler {

    private final TypeHopitalService typeHopitalService;

    @EventHandler
    @Transactional
    protected void onCreatedEvent(TypeHopitalCreatedEvent event) {
        event.getTypeHopital().setIdTypeHopital(event.getId());
        typeHopitalService.create(event.getTypeHopital());
    }

    @EventHandler
    @Transactional
    protected void onUpdatedEvent(TypeHopitalUpdatedEvent event) {
        typeHopitalService.update(event.getTypeHopital());
    }

    @EventHandler
    @Transactional
    protected void onDeletedEvent(TypeHopitalDeletedEvent event) {
        typeHopitalService.delete(typeHopitalService.findOne(event.getId()));
    }
}
