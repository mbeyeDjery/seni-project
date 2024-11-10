package fr.app.seni.core.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link fr.app.seni.core.domain.Etablissement}
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EtablissementDto implements Serializable {

    private String idEtablissemnt;
    private String codeEtablissement;
    private String nom;
    private String sigle;
    private String slogan;
    private String logo;
    private String addresse;
    private String telephone;
    private String mobile;
    private String email;
    private String fax;
    private String siteWeb;
    private String statut;
    private Boolean online;
}