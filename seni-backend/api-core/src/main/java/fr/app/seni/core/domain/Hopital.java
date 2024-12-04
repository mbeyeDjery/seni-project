package fr.app.seni.core.domain;

import fr.app.seni.core.enums.HopitalStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@ToString
@Entity
@Table(name = "hopital")
public class Hopital {

    @Id
    @Column(name = "id_hopital", nullable = false, length = 254)
    private String idHopital;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_ville", nullable = false)
    private Ville ville;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_type_hopital", nullable = false)
    private TypeHopital typeHopital;

    @Column(name = "code_hopital", nullable = false, length = 254)
    private String codeHopital;

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

    @Column(name = "longitude", length = 254)
    private String longitude;

    @Column(name = "latitude", length = 254)
    private String latitude;

    @Column(name = "presentatopn", length = 254)
    private String presentatopn;

    @Column(name = "note", length = 254)
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false, length = 254)
    private HopitalStatus statut;

    @Column(name = "online")
    private Boolean online;

}