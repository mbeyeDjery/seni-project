package fr.app.seni.core.mapper;

import fr.app.seni.core.domain.AppUser;
import fr.app.seni.core.dto.AppUserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppUserMapper extends EntityMapper<AppUserDto, AppUser> {
}