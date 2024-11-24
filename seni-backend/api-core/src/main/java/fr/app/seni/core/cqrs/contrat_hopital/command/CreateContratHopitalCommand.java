package fr.app.seni.core.cqrs.contrat_hopital.command;

import fr.app.seni.core.cqrs.BaseCommand;
import fr.app.seni.core.dto.ContratHopitalDto;
import lombok.Getter;

@Getter
public class CreateContratHopitalCommand extends BaseCommand<String> {

    private final ContratHopitalDto contratHopital;

    public CreateContratHopitalCommand(String id, ContratHopitalDto contratHopital) {
        super(id);
        this.contratHopital = contratHopital;
    }
}
