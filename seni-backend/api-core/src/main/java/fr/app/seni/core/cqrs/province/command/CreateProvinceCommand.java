package fr.app.seni.core.cqrs.province.command;

import fr.app.seni.core.cqrs.BaseCommand;
import fr.app.seni.core.dto.ProvinceDto;
import lombok.Getter;

@Getter
public class CreateProvinceCommand extends BaseCommand<String> {

    private final ProvinceDto province;

    public CreateProvinceCommand(String id, ProvinceDto province) {
        super(id);
        this.province = province;
    }
}
