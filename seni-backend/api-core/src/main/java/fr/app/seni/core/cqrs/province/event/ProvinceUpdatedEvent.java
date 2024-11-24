package fr.app.seni.core.cqrs.province.event;

import fr.app.seni.core.cqrs.BaseEvent;
import fr.app.seni.core.dto.ProvinceDto;
import lombok.Getter;

@Getter
public class ProvinceUpdatedEvent extends BaseEvent<String> {

    private final ProvinceDto province;

    public ProvinceUpdatedEvent(String id, ProvinceDto province) {
        super(id);
        this.province = province;
    }
}
