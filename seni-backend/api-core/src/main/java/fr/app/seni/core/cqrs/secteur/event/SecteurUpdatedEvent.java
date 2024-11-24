package fr.app.seni.core.cqrs.secteur.event;

import fr.app.seni.core.cqrs.BaseEvent;
import fr.app.seni.core.dto.SecteurDto;
import lombok.Getter;

@Getter
public class SecteurUpdatedEvent extends BaseEvent<String> {

    private final SecteurDto secteur;

    public SecteurUpdatedEvent(String id, SecteurDto secteur) {
        super(id);
        this.secteur = secteur;
    }
}