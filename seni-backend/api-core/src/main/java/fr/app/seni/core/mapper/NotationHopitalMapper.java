package fr.app.seni.core.mapper;

import fr.app.seni.core.domain.NotationHopital;
import fr.app.seni.core.dto.NotationHopitalDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {HopitalMapper.class})
public interface NotationHopitalMapper extends EntityMapper<NotationHopitalDto, NotationHopital> {
}