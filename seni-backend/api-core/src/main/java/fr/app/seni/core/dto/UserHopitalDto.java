package fr.app.seni.core.dto;

import fr.app.seni.core.domain.UserHopitalId;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link fr.app.seni.core.domain.UserHopital}
 */
@Value
public class UserHopitalDto implements Serializable {
    UserHopitalId id;
    AppUserDto user;
    HopitalDto hopital;
}