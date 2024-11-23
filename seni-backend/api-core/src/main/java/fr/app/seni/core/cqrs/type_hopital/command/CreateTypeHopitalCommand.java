package fr.app.seni.core.cqrs.type_hopital.command;

import fr.app.seni.core.cqrs.BaseCommand;
import fr.app.seni.core.dto.TypeHopitalDto;
import lombok.Getter;

@Getter
public class CreateTypeHopitalCommand extends BaseCommand<String> {

    private final TypeHopitalDto typeHopitalDto;

    public CreateTypeHopitalCommand(String id, TypeHopitalDto typeHopitalDto) {
        super(id);
        this.typeHopitalDto = typeHopitalDto;
    }
}
