package fr.app.seni.core.cqrs.hopital.event;

import fr.app.seni.core.cqrs.BaseEvent;
import fr.app.seni.core.dto.HopitalDto;
import lombok.Getter;

@Getter
public class HopitalUpdatedEvent extends BaseEvent<String> {

    private HopitalDto hopitalDto;

    public HopitalUpdatedEvent(String id, HopitalDto hopitalDto) {
        super(id);
        this.hopitalDto = hopitalDto;
    }
}
