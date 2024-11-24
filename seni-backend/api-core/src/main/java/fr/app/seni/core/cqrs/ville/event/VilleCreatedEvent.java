package fr.app.seni.core.cqrs.ville.event;

import fr.app.seni.core.cqrs.BaseEvent;
import fr.app.seni.core.dto.VilleDto;
import lombok.Getter;

@Getter
public class VilleCreatedEvent extends BaseEvent<String> {

    private final  VilleDto ville;

    public VilleCreatedEvent(String id, VilleDto ville) {
        super(id);
        this.ville = ville;
    }
}
