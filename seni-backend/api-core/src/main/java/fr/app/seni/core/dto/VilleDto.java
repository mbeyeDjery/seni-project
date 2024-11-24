package fr.app.seni.core.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link fr.app.seni.core.domain.Ville}
 */
@Data
@Builder
public class VilleDto implements Serializable {
    String idVille;
    ProvinceDto province;
    String nom;
}