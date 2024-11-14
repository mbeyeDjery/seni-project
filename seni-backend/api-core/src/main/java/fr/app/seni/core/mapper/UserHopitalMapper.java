package fr.app.seni.core.mapper;

import fr.app.seni.core.domain.UserHopital;
import fr.app.seni.core.dto.UserHopitalDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {AppUserMapper.class, HopitalMapper.class})
public interface UserHopitalMapper extends EntityMapper<UserHopitalDto, UserHopital> {
}