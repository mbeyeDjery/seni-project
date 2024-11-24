package fr.app.seni.parametre.eventhandler;


import fr.app.seni.core.cqrs.ville.event.VilleCreatedEvent;
import fr.app.seni.core.cqrs.ville.event.VilleDeletedEvent;
import fr.app.seni.core.cqrs.ville.event.VilleUpdatedEvent;
import fr.app.seni.core.service.VilleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class VilleEventHandler {

    private final VilleService villeService;

    @EventHandler
    @Transactional
    protected void onCreatedEvent(VilleCreatedEvent event) {
        event.getVille().setIdVille(event.getId());
        villeService.create(event.getVille());
    }

    @EventHandler
    @Transactional
    protected void onUpdatedEvent(VilleUpdatedEvent event) {
        villeService.update(event.getVille());
    }

    @EventHandler
    @Transactional
    protected void onDeletedEvent(VilleDeletedEvent event) {
        villeService.delete(villeService.findOne(event.getId()));
    }
}
