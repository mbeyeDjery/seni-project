package fr.app.seni.core.cqrs.type_etablissement.command;

import fr.app.seni.core.cqrs.BaseCommand;
import lombok.Getter;

@Getter
public class DeleteTypeEtablissementCommand extends BaseCommand<String> {

    private final String idTypeEtab;

    public DeleteTypeEtablissementCommand(String id, String idTypeEtab) {
        super(id);
        this.idTypeEtab = idTypeEtab;
    }
}
