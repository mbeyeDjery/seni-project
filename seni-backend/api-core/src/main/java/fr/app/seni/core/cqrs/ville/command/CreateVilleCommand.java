package fr.app.seni.core.cqrs.ville.command;

import fr.app.seni.core.cqrs.BaseCommand;
import fr.app.seni.core.dto.RegionDto;
import fr.app.seni.core.dto.VilleDto;
import lombok.Getter;

@Getter
public class CreateVilleCommand extends BaseCommand<String> {

    private final VilleDto ville;

    public CreateVilleCommand(String id, VilleDto ville) {
        super(id);
        this.ville = ville;
    }
}
