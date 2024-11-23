package fr.app.seni.core.cqrs.hopital.command;

import fr.app.seni.core.cqrs.BaseCommand;
import fr.app.seni.core.dto.HopitalDto;
import lombok.Getter;

@Getter
public class UpdateHopitalCommand extends BaseCommand<String> {

    private final HopitalDto hopitalDto;

    public UpdateHopitalCommand(String id, HopitalDto hopitalDto) {
        super(id);
        this.hopitalDto = hopitalDto;
    }
}
