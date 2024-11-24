package fr.app.seni.core.cqrs.secteur.command;

import fr.app.seni.core.cqrs.BaseCommand;
import lombok.Getter;

@Getter
public class DeleteSecteurCommand extends BaseCommand<String> {

    public DeleteSecteurCommand(String id) {
        super(id);
    }
}
