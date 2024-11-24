package fr.app.seni.core.cqrs.region.event;

import fr.app.seni.core.cqrs.BaseEvent;
import fr.app.seni.core.dto.RegionDto;
import lombok.Getter;

@Getter
public class RegionUpdatedEvent extends BaseEvent<String> {

    private final RegionDto region;

    public RegionUpdatedEvent(String id, RegionDto region) {
        super(id);
        this.region = region;
    }
}
