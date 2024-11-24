package fr.app.seni.core.mapper;

import fr.app.seni.core.domain.Hopital;
import fr.app.seni.core.dto.HopitalDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {TypeHopitalMapper.class})
public interface HopitalMapper extends EntityMapper<HopitalDto, Hopital> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Hopital partialUpdate(HopitalDto hopitalDto, @MappingTarget Hopital hopital);
}