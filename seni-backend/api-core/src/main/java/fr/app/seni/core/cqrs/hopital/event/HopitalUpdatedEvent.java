package fr.app.seni.core.cqrs.hopital.event;

import fr.app.seni.core.cqrs.BaseEvent;
import fr.app.seni.core.dto.HopitalDto;
import lombok.Getter;

@Getter
public class HopitalUpdatedEvent extends BaseEvent<String> {

    private final HopitalDto hopital;

    public HopitalUpdatedEvent(String id, HopitalDto hopital) {
        super(id);
        this.hopital = hopital;
    }
}
