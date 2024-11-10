package fr.app.seni.core.mapper;

import fr.app.seni.core.domain.TypeEtablissement;
import fr.app.seni.core.dto.TypeEtablissementDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeEtablissementMapper extends EntityMapper<TypeEtablissementDto, TypeEtablissement>{
}
