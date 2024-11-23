package fr.app.seni.core.mapper;

import fr.app.seni.core.domain.Hopital;
import fr.app.seni.core.dto.HopitalDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {TypeHopitalMapper.class})
public interface HopitalMapper extends EntityMapper<HopitalDto, Hopital> {
}