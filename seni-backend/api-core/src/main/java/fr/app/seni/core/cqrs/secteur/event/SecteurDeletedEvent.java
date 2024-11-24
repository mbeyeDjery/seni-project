package fr.app.seni.core.cqrs.secteur.event;

import fr.app.seni.core.cqrs.BaseEvent;
import lombok.Getter;

@Getter
public class SecteurDeletedEvent extends BaseEvent<String> {
    public SecteurDeletedEvent(String id) {
        super(id);
    }
}
