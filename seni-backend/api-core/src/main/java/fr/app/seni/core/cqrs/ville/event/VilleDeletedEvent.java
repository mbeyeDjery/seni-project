package fr.app.seni.core.cqrs.ville.event;

import fr.app.seni.core.cqrs.BaseEvent;
import lombok.Getter;

@Getter
public class VilleDeletedEvent extends BaseEvent<String> {
    public VilleDeletedEvent(String id) {
        super(id);
    }
}
