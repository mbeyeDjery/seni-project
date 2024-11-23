package fr.app.seni.core.cqrs.hopital.event;

import fr.app.seni.core.cqrs.BaseCommand;
import fr.app.seni.core.enums.HopitalStatus;
import lombok.Getter;

@Getter
public class HopitalStatusChangedEvent extends BaseCommand<String> {

    private final HopitalStatus hopitalStatus;

    public HopitalStatusChangedEvent(String id, HopitalStatus hopitalStatus) {
        super(id);
        this.hopitalStatus = hopitalStatus;
    }
}
