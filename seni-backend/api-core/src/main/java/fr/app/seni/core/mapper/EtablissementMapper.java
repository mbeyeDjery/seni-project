package fr.app.seni.core.mapper;

import fr.app.seni.core.domain.Etablissement;
import fr.app.seni.core.dto.EtablissementDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EtablissementMapper extends EntityMapper<EtablissementDto, Etablissement>{
}
