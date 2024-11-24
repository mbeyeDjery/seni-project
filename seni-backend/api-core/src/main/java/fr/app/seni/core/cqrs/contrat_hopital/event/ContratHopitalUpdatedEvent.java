package fr.app.seni.core.cqrs.contrat_hopital.event;

import fr.app.seni.core.cqrs.BaseEvent;
import fr.app.seni.core.dto.ContratHopitalDto;
import lombok.Getter;

@Getter
public class ContratHopitalUpdatedEvent extends BaseEvent<String> {

    private final ContratHopitalDto contratHopital;

    public ContratHopitalUpdatedEvent(String id, ContratHopitalDto contratHopital) {
        super(id);
        this.contratHopital = contratHopital;
    }
}
