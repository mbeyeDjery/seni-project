package fr.app.seni.core.cqrs.region.command;

import fr.app.seni.core.cqrs.BaseCommand;
import fr.app.seni.core.dto.RegionDto;
import lombok.Getter;

@Getter
public class UpdateRegionCommand extends BaseCommand<String> {

    private final RegionDto region;

    public UpdateRegionCommand(String id, RegionDto region) {
        super(id);
        this.region = region;
    }
}
