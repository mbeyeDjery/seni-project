package fr.app.seni.core.cqrs.type_hopital.event;

import fr.app.seni.core.cqrs.BaseEvent;
import fr.app.seni.core.dto.TypeHopitalDto;
import lombok.Getter;

@Getter
public class TypeHopitalUpdatedEvent extends BaseEvent<String> {

    private final TypeHopitalDto typeHopital;

    public TypeHopitalUpdatedEvent(String id, TypeHopitalDto typeHopital) {
        super(id);
        this.typeHopital = typeHopital;
    }
}
