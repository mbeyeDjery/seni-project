package fr.app.seni.core.cqrs.contrat_hopital.event;

import fr.app.seni.core.cqrs.BaseEvent;
import lombok.Getter;

@Getter
public class ContratHopitalDeletedEvent extends BaseEvent<String> {
    public ContratHopitalDeletedEvent(String id) {
        super(id);
    }
}
