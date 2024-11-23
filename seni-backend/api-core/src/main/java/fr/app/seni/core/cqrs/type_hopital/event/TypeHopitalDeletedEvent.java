package fr.app.seni.core.cqrs.type_hopital.event;

import fr.app.seni.core.cqrs.BaseEvent;
import fr.app.seni.core.dto.TypeHopitalDto;
import lombok.Getter;

@Getter
public class TypeHopitalDeletedEvent extends BaseEvent<String> {
    public TypeHopitalDeletedEvent(String id) {
        super(id);
    }
}
