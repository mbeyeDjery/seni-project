package fr.app.seni.core.cqrs.type_etablissement.query;

import fr.app.seni.core.cqrs.BaseEvent;
import fr.app.seni.core.domain.TypeEtablissement;
import fr.app.seni.core.dto.TypeEtablissementDto;
import lombok.Getter;

@Getter
public class TypeEtablissementUpdatedEvent extends BaseEvent<String> {

    private final TypeEtablissementDto typeEtablissementDto;

    public TypeEtablissementUpdatedEvent(String id, TypeEtablissementDto typeEtablissementDto) {
        super(id);
        this.typeEtablissementDto = typeEtablissementDto;
    }
}
