package fr.app.seni.core.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link fr.app.seni.core.domain.Region}
 */
@Data
@Builder
public class RegionDto implements Serializable {
    String idRegion;
    String codeInsd;
    String nom;
    String description;
}