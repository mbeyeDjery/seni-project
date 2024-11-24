package fr.app.seni.core.mapper;

import fr.app.seni.core.domain.ContratHopital;
import fr.app.seni.core.dto.ContratHopitalDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {HopitalMapper.class})
public interface ContratHopitalMapper extends EntityMapper<ContratHopitalDto, ContratHopital> {
}