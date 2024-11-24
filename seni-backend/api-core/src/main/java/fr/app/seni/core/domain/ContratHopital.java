package fr.app.seni.core.domain;

import fr.app.seni.core.enums.ContratHopitalStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@Table(name = "contrat_hopital")
public class ContratHopital {

    @Id
    @Column(name = "id_contratt_hopital", nullable = false, length = 254)
    private String idContrattHopital;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_hopital", nullable = false)
    @ToString.Exclude
    private Hopital hopital;

    @Column(name = "reference", nullable = false, length = 254)
    private String reference;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin;

    @Column(name = "note", length = 254)
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false, length = 254)
    private ContratHopitalStatus statut;

    @Column(name = "enabled")
    private Boolean enabled;

}