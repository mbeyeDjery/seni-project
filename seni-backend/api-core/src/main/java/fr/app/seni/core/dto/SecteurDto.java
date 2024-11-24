package fr.app.seni.core.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link fr.app.seni.core.domain.Secteur}
 */
@Data
@Builder
public class SecteurDto implements Serializable {
    String idSecteur;
    VilleDto ville;
    String libelle;
}