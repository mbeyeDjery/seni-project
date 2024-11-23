package fr.app.seni.core.cqrs.hopital.event;

import fr.app.seni.core.cqrs.BaseEvent;
import lombok.Getter;

@Getter
public class HopitalDeletedEvent extends BaseEvent<String> {
    public HopitalDeletedEvent(String id) {
        super(id);
    }
}
