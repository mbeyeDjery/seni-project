package fr.app.seni.core.mapper;

import fr.app.seni.core.domain.Province;
import fr.app.seni.core.dto.ProvinceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RegionMapper.class})
public interface ProvinceMapper extends EntityMapper<ProvinceDto, Province> {
}