package fr.app.seni.core.mapper;

import fr.app.seni.core.domain.Ville;
import fr.app.seni.core.dto.VilleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProvinceMapper.class})
public interface VilleMapper extends EntityMapper<VilleDto, Ville> {
}