package fr.app.seni.core.cqrs.contrat_hopital.event;

import fr.app.seni.core.cqrs.BaseCommand;
import fr.app.seni.core.enums.ContratHopitalStatus;
import lombok.Getter;

@Getter
public class ContratHopitalStatusChangedEvent extends BaseCommand<String> {

    private final ContratHopitalStatus statut;

    public ContratHopitalStatusChangedEvent(String id, ContratHopitalStatus statut) {
        super(id);
        this.statut = statut;
    }
}
