package fr.app.seni.core.cqrs.secteur.command;

import fr.app.seni.core.cqrs.BaseCommand;
import fr.app.seni.core.dto.SecteurDto;
import lombok.Getter;

@Getter
public class CreateSecteurCommand extends BaseCommand<String> {

    private final SecteurDto secteur;

    public CreateSecteurCommand(String id, SecteurDto secteur) {
        super(id);
        this.secteur = secteur;
    }
}
