package fr.app.seni.core.cqrs.type_hopital.command;

import fr.app.seni.core.cqrs.BaseCommand;
import lombok.Getter;

@Getter
public class DeleteTypeHopitalCommand extends BaseCommand<String> {

    public DeleteTypeHopitalCommand(String id) {
        super(id);
    }
}
