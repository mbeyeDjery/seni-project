package fr.app.seni.core.cqrs.secteur.command;

import fr.app.seni.core.cqrs.BaseCommand;
import fr.app.seni.core.dto.SecteurDto;
import lombok.Getter;

@Getter
public class UpdateSecteurCommand extends BaseCommand<String> {

    private final SecteurDto secteur;

    public UpdateSecteurCommand(String id, SecteurDto secteur) {
        super(id);
        this.secteur = secteur;
    }
}
