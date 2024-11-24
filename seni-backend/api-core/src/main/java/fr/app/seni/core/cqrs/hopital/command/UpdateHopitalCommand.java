package fr.app.seni.core.cqrs.hopital.command;

import fr.app.seni.core.cqrs.BaseCommand;
import fr.app.seni.core.dto.HopitalDto;
import lombok.Getter;

@Getter
public class UpdateHopitalCommand extends BaseCommand<String> {

    private final HopitalDto hopital;

    public UpdateHopitalCommand(String id, HopitalDto hopital) {
        super(id);
        this.hopital = hopital;
    }
}
