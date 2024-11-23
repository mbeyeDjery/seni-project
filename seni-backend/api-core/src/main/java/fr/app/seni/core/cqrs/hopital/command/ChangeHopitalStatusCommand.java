package fr.app.seni.core.cqrs.hopital.command;

import fr.app.seni.core.cqrs.BaseCommand;
import fr.app.seni.core.enums.HopitalStatus;
import lombok.Getter;

@Getter
public class ChangeHopitalStatusCommand extends BaseCommand<String> {

    private final HopitalStatus hopitalStatus;

    public ChangeHopitalStatusCommand(String id, HopitalStatus hopitalStatus) {
        super(id);
        this.hopitalStatus = hopitalStatus;
    }
}
