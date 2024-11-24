package fr.app.seni.core.cqrs.notation_hopital.command;

import fr.app.seni.core.cqrs.BaseCommand;
import fr.app.seni.core.dto.NotationHopitalDto;
import lombok.Getter;

@Getter
public class UpdateNotationHopitalCommand extends BaseCommand<String> {

    private final NotationHopitalDto notationHopital;

    public UpdateNotationHopitalCommand(String id, NotationHopitalDto notationHopital) {
        super(id);
        this.notationHopital = notationHopital;
    }
}
