package fr.app.seni.hopital.eventhandler;


import fr.app.seni.core.cqrs.contrat_hopital.event.ContratHopitalCreatedEvent;
import fr.app.seni.core.cqrs.contrat_hopital.event.ContratHopitalDeletedEvent;
import fr.app.seni.core.cqrs.contrat_hopital.event.ContratHopitalStatusChangedEvent;
import fr.app.seni.core.cqrs.contrat_hopital.event.ContratHopitalUpdatedEvent;
import fr.app.seni.core.dto.ContratHopitalDto;
import fr.app.seni.core.service.ContratHopitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ContratHopitalEventHandler {

    private final ContratHopitalService contratHopitalService;

    @EventHandler
    @Transactional
    protected void onCreatedEvent(ContratHopitalCreatedEvent event) {
        event.getContratHopital().setIdContrattHopital(event.getId());
        contratHopitalService.create(event.getContratHopital());
    }

    @EventHandler
    @Transactional
    protected void onUpdatedEvent(ContratHopitalUpdatedEvent event) {
        contratHopitalService.update(event.getContratHopital());
    }

    @EventHandler
    @Transactional
    protected void onDeletedEvent(ContratHopitalDeletedEvent event) {
        contratHopitalService.delete(contratHopitalService.findOne(event.getId()));
    }

    @EventHandler
    @Transactional
    protected void onChangedStatusEvent(ContratHopitalStatusChangedEvent event) {
        ContratHopitalDto contratHopitalDto = contratHopitalService.findOne(event.getId());
        if (contratHopitalDto != null) {
            contratHopitalDto.setStatut(event.getStatut());
            contratHopitalService.update(contratHopitalDto);
        }
    }
}
