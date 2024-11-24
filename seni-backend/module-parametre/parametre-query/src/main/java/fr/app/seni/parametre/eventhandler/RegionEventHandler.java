package fr.app.seni.parametre.eventhandler;


import fr.app.seni.core.cqrs.region.event.RegionCreatedEvent;
import fr.app.seni.core.cqrs.region.event.RegionDeletedEvent;
import fr.app.seni.core.cqrs.region.event.RegionUpdatedEvent;
import fr.app.seni.core.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegionEventHandler {

    private final RegionService regionService;

    @EventHandler
    @Transactional
    protected void onCreatedEvent(RegionCreatedEvent event) {
        event.getRegion().setIdRegion(event.getId());
        regionService.create(event.getRegion());
    }

    @EventHandler
    @Transactional
    protected void onUpdatedEvent(RegionUpdatedEvent event) {
        regionService.update(event.getRegion());
    }

    @EventHandler
    @Transactional
    protected void onDeletedEvent(RegionDeletedEvent event) {
        regionService.delete(regionService.findOne(event.getId()));
    }
}
