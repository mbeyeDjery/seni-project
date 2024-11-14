package fr.app.seni.core.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link fr.app.seni.core.domain.TypeHopital}
 */
@Data
@Builder
public class TypeHopitalDto implements Serializable {

    String idTypeHopital;
    String libelle;
    String description;
    Boolean enabled;
}