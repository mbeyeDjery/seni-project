package fr.app.seni.core.cqrs.notation_hopital.command;

import fr.app.seni.core.cqrs.BaseCommand;
import lombok.Getter;

@Getter
public class DeleteNotationHopitalCommand extends BaseCommand<String> {

    public DeleteNotationHopitalCommand(String id) {
        super(id);
    }
}
