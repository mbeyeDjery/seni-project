package fr.app.seni.core.cqrs.region.event;

import fr.app.seni.core.cqrs.BaseEvent;
import lombok.Getter;

@Getter
public class RegionDeletedEvent extends BaseEvent<String> {
    public RegionDeletedEvent(String id) {
        super(id);
    }
}
