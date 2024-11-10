package fr.app.seni.core.domain;

import fr.app.seni.core.enums.EtablissementStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "etablissement")
public class Etablissement extends AbstractAuditingEntity {
    @Id
    @Column(name = "id_etablissemnt", nullable = false, length = 254)
    private String idEtablissemnt;

    @Column(name = "code_etablissement", nullable = false, length = 254)
    private String codeEtablissement;

    @Column(name = "nom", nullable = false, length = 254)
    private String nom;

    @Column(name = "sigle", length = 254)
    private String sigle;

    @Column(name = "slogan", length = 254)
    private String slogan;

    @Column(name = "logo", length = 254)
    private String logo;

    @Column(name = "addresse", length = 254)
    private String addresse;

    @Column(name = "telephone", nullable = false, length = 254)
    private String telephone;

    @Column(name = "mobile", length = 254)
    private String mobile;

    @Column(name = "email", length = 254)
    private String email;


    @Column(name = "fax", length = 254)
    private String fax;

    @Column(name = "site_web", length = 254)
    private String siteWeb;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false, length = 254)
    private EtablissementStatus statut;

    @Column(name = "online")
    private Boolean online;

}