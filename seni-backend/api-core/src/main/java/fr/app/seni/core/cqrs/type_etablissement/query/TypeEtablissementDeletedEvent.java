package fr.app.seni.core.cqrs.type_etablissement.query;

import fr.app.seni.core.cqrs.BaseEvent;
import fr.app.seni.core.domain.TypeEtablissement;
import lombok.Getter;

@Getter
public class TypeEtablissementDeletedEvent extends BaseEvent<String> {

    private final String idTypeEtab;

    public TypeEtablissementDeletedEvent(String id, String idTypeEtab) {
        super(id);
        this.idTypeEtab = idTypeEtab;
    }
}
