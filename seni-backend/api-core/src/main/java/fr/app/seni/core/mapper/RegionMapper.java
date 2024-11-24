package fr.app.seni.core.mapper;

import fr.app.seni.core.domain.Region;
import fr.app.seni.core.dto.RegionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegionMapper extends EntityMapper<RegionDto, Region> {

}