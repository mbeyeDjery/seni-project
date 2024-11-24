package fr.app.seni.core.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link fr.app.seni.core.domain.NotationHopital}
 */
@Data
@Builder
public class NotationHopitalDto implements Serializable {
    String idNotation;
    HopitalDto hopital;
    String idUser;
    Double note;
}