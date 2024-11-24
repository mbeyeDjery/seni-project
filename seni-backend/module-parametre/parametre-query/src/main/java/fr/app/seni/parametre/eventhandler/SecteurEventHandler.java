package fr.app.seni.parametre.eventhandler;


import fr.app.seni.core.cqrs.secteur.event.SecteurCreatedEvent;
import fr.app.seni.core.cqrs.secteur.event.SecteurDeletedEvent;
import fr.app.seni.core.cqrs.secteur.event.SecteurUpdatedEvent;
import fr.app.seni.core.service.SecteurService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecteurEventHandler {

    private final SecteurService secteurService;

    @EventHandler
    @Transactional
    protected void onCreatedEvent(SecteurCreatedEvent event) {
        event.getSecteur().setIdSecteur(event.getId());
        secteurService.create(event.getSecteur());
    }

    @EventHandler
    @Transactional
    protected void onUpdatedEvent(SecteurUpdatedEvent event) {
        secteurService.update(event.getSecteur());
    }

    @EventHandler
    @Transactional
    protected void onDeletedEvent(SecteurDeletedEvent event) {
        secteurService.delete(secteurService.findOne(event.getId()));
    }
}
