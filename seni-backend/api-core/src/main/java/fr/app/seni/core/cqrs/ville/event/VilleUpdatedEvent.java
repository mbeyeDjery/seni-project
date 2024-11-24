package fr.app.seni.core.cqrs.ville.event;

import fr.app.seni.core.cqrs.BaseEvent;
import fr.app.seni.core.dto.VilleDto;
import lombok.Getter;

@Getter
public class VilleUpdatedEvent extends BaseEvent<String> {

    private final  VilleDto ville;

    public VilleUpdatedEvent(String id, VilleDto ville) {
        super(id);
        this.ville = ville;
    }
}
