package fr.app.seni.core.cqrs.province.event;

import fr.app.seni.core.cqrs.BaseEvent;
import lombok.Getter;

@Getter
public class ProvinceDeletedEvent extends BaseEvent<String> {
    public ProvinceDeletedEvent(String id) {
        super(id);
    }
}
