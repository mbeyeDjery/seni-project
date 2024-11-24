package fr.app.seni.core.cqrs.contrat_hopital.command;

import fr.app.seni.core.cqrs.BaseCommand;
import lombok.Getter;

@Getter
public class DeleteContratHopitalCommand extends BaseCommand<String> {

    public DeleteContratHopitalCommand(String id) {
        super(id);
    }
}
