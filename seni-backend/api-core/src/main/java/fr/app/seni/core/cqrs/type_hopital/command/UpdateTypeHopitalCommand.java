package fr.app.seni.core.cqrs.type_hopital.command;

import fr.app.seni.core.cqrs.BaseCommand;
import fr.app.seni.core.dto.TypeHopitalDto;
import lombok.Getter;

@Getter
public class UpdateTypeHopitalCommand extends BaseCommand<String> {

    private final TypeHopitalDto typeHopital;

    public UpdateTypeHopitalCommand(String id, TypeHopitalDto typeHopital) {
        super(id);
        this.typeHopital = typeHopital;
    }
}
