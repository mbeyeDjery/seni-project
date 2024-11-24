package fr.app.seni.core.cqrs.province.command;

import fr.app.seni.core.cqrs.BaseCommand;
import lombok.Getter;

@Getter
public class DeleteProvinceCommand extends BaseCommand<String> {

    public DeleteProvinceCommand(String id) {
        super(id);
    }
}
