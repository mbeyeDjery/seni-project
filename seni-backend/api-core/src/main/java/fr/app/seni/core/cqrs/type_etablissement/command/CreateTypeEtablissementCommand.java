package fr.app.seni.core.cqrs.type_etablissement.command;

import fr.app.seni.core.cqrs.BaseCommand;
import fr.app.seni.core.dto.TypeEtablissementDto;
import lombok.Getter;

@Getter
public class CreateTypeEtablissementCommand extends BaseCommand<String> {

    private final TypeEtablissementDto typeEtablissementDto;

    public CreateTypeEtablissementCommand(String id, TypeEtablissementDto typeEtablissementDto) {
        super(id);
        this.typeEtablissementDto = typeEtablissementDto;
    }
}
