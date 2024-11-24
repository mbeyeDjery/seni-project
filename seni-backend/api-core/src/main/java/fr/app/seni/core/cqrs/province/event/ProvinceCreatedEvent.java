package fr.app.seni.core.cqrs.province.event;

import fr.app.seni.core.cqrs.BaseEvent;
import fr.app.seni.core.dto.ProvinceDto;
import lombok.Getter;

@Getter
public class ProvinceCreatedEvent extends BaseEvent<String> {

    private final ProvinceDto province;

    public ProvinceCreatedEvent(String id, ProvinceDto province) {
        super(id);
        this.province = province;
    }
}
