package fr.app.seni.core.dto;

import fr.app.seni.core.dto.TypeHopitalDto;
import fr.app.seni.core.enums.HopitalStatus;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link fr.app.seni.core.domain.Hopital}
 */
@Value
public class HopitalDto implements Serializable {

    String idHopital;
    TypeHopitalDto typeHopital;
    String codeHopital;
    String nom;
    String sigle;
    String slogan;
    String logo;
    String addresse;
    String telephone;
    String mobile;
    String email;
    String fax;
    String siteWeb;
    String longitude;
    String latitude;
    HopitalStatus statut;
    Boolean online;
}