package fr.app.seni.core.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link fr.app.seni.core.domain.TypeEtablissement}
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TypeEtablissementDto implements Serializable {

    private String idTypeEtab;
    private String libelle;
    private String description;
    private Boolean enabled;
}