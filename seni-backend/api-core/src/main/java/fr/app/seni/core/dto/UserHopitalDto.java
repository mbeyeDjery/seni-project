package fr.app.seni.core.dto;

import fr.app.seni.core.domain.UserHopitalId;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * DTO for {@link fr.app.seni.core.domain.UserHopital}
 */
@Data
@Builder
@ToString
public class UserHopitalDto implements Serializable {
    UserHopitalId id;
    AppUserDto user;
    HopitalDto hopital;
}