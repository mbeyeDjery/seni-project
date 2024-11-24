package fr.app.seni.parametre.eventhandler;


import fr.app.seni.core.cqrs.province.event.ProvinceCreatedEvent;
import fr.app.seni.core.cqrs.province.event.ProvinceDeletedEvent;
import fr.app.seni.core.cqrs.province.event.ProvinceUpdatedEvent;
import fr.app.seni.core.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProvinceEventHandler {

    private final ProvinceService provinceService;

    @EventHandler
    @Transactional
    protected void onCreatedEvent(ProvinceCreatedEvent event) {
        event.getProvince().setIdProvince(event.getId());
        provinceService.create(event.getProvince());
    }

    @EventHandler
    @Transactional
    protected void onUpdatedEvent(ProvinceUpdatedEvent event) {
        provinceService.update(event.getProvince());
    }

    @EventHandler
    @Transactional
    protected void onDeletedEvent(ProvinceDeletedEvent event) {
        provinceService.delete(provinceService.findOne(event.getId()));
    }
}
