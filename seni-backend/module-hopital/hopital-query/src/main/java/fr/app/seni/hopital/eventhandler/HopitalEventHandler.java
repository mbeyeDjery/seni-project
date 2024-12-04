package fr.app.seni.hopital.eventhandler;


import fr.app.seni.core.cqrs.hopital.event.HopitalCreatedEvent;
import fr.app.seni.core.cqrs.hopital.event.HopitalDeletedEvent;
import fr.app.seni.core.cqrs.hopital.event.HopitalStatusChangedEvent;
import fr.app.seni.core.cqrs.hopital.event.HopitalUpdatedEvent;
import fr.app.seni.core.dto.HopitalDto;
import fr.app.seni.core.service.HopitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class HopitalEventHandler {

    private final HopitalService hopitalService;

    @EventHandler
    @Transactional
    protected void onCreatedEvent(HopitalCreatedEvent event) {
        event.getHopital().setIdHopital(event.getId());
        event.getHopital().getVille().setIdVille(event.getHopital().getVille().getIdVille());
        hopitalService.create(event.getHopital());
    }

    @EventHandler
    @Transactional
    protected void onUpdatedEvent(HopitalUpdatedEvent event) {
        hopitalService.update(event.getHopital());
    }

    @EventHandler
    @Transactional
    protected void onDeletedEvent(HopitalDeletedEvent event) {
        hopitalService.delete(hopitalService.findOne(event.getId()));
    }

    @EventHandler
    @Transactional
    protected void onChangedStatusEvent(HopitalStatusChangedEvent event) {
        HopitalDto hopital = hopitalService.findOne(event.getId());
        if (hopital != null) {
            hopital.setStatut(event.getStatut());
            hopitalService.update(hopital);
        }
    }
}
