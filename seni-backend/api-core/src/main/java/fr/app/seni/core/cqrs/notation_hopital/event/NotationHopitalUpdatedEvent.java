package fr.app.seni.core.cqrs.notation_hopital.event;

import fr.app.seni.core.cqrs.BaseEvent;
import fr.app.seni.core.dto.NotationHopitalDto;
import lombok.Getter;

@Getter
public class NotationHopitalUpdatedEvent extends BaseEvent<String> {

    private final NotationHopitalDto notationHopital;

    public NotationHopitalUpdatedEvent(String id, NotationHopitalDto notationHopital) {
        super(id);
        this.notationHopital = notationHopital;
    }
}
