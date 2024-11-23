package fr.app.seni.core.mapper;

import fr.app.seni.core.domain.TypeHopital;
import fr.app.seni.core.dto.TypeHopitalDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TypeHopitalMapper extends EntityMapper<TypeHopitalDto, TypeHopital> {
}