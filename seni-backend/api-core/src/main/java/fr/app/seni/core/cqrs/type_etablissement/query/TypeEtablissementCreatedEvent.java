package fr.app.seni.core.cqrs.type_etablissement.query;

import fr.app.seni.core.cqrs.BaseCommand;
import fr.app.seni.core.cqrs.BaseEvent;
import fr.app.seni.core.domain.TypeEtablissement;
import fr.app.seni.core.dto.TypeEtablissementDto;
import lombok.Getter;

@Getter
public class TypeEtablissementCreatedEvent extends BaseEvent<String> {

    private final TypeEtablissementDto typeEtablissementDto;

    public TypeEtablissementCreatedEvent(String id, TypeEtablissementDto typeEtablissementDto) {
        super(id);
        this.typeEtablissementDto = typeEtablissementDto;
    }
}
