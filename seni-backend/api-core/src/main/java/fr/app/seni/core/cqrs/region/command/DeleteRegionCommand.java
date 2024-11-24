package fr.app.seni.core.cqrs.region.command;

import fr.app.seni.core.cqrs.BaseCommand;
import lombok.Getter;

@Getter
public class DeleteRegionCommand extends BaseCommand<String> {

    public DeleteRegionCommand(String id) {
        super(id);
    }
}
