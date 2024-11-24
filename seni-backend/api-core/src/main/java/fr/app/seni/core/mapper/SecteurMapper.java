package fr.app.seni.core.mapper;

import fr.app.seni.core.domain.Secteur;
import fr.app.seni.core.dto.SecteurDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {VilleMapper.class})
public interface SecteurMapper extends EntityMapper<SecteurDto, Secteur> {
}