package fr.app.seni.core.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link fr.app.seni.core.domain.Province}
 */
@Data
@Builder
public class ProvinceDto implements Serializable {
    String idProvince;
    RegionDto region;
    String nom;
}