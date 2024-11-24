package fr.app.seni.core.cqrs.notation_hopital.event;

import fr.app.seni.core.cqrs.BaseEvent;
import lombok.Getter;

@Getter
public class NotationHopitalDeletedEvent extends BaseEvent<String> {
    public NotationHopitalDeletedEvent(String id) {
        super(id);
    }
}
