package fr.app.seni.core.dto;

import fr.app.seni.core.enums.ContratHopitalStatus;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link fr.app.seni.core.domain.ContratHopital}
 */
@Data
@Builder
public class ContratHopitalDto implements Serializable {
    String idContrattHopital;
    HopitalDto hopital;
    String reference;
    LocalDate dateDebut;
    LocalDate dateFin;
    String note;
    ContratHopitalStatus statut;
    Boolean enabled;
}