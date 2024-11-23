package fr.app.seni.core.cqrs.hopital.command;

import fr.app.seni.core.cqrs.BaseCommand;
import lombok.Getter;

@Getter
public class DeleteHopitalCommand extends BaseCommand<String> {

    public DeleteHopitalCommand(String id) {
        super(id);
    }
}
