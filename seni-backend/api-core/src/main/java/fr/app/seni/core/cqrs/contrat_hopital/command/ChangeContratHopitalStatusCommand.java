package fr.app.seni.core.cqrs.contrat_hopital.command;

import fr.app.seni.core.cqrs.BaseCommand;
import fr.app.seni.core.enums.ContratHopitalStatus;
import lombok.Getter;

@Getter
public class ChangeContratHopitalStatusCommand extends BaseCommand<String> {

    private final ContratHopitalStatus statut;

    public ChangeContratHopitalStatusCommand(String id, ContratHopitalStatus statut) {
        super(id);
        this.statut = statut;
    }
}
