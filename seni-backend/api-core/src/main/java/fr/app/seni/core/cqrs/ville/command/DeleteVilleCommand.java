package fr.app.seni.core.cqrs.ville.command;

import fr.app.seni.core.cqrs.BaseCommand;
import lombok.Getter;

@Getter
public class DeleteVilleCommand extends BaseCommand<String> {

    public DeleteVilleCommand(String id) {
        super(id);
    }
}
