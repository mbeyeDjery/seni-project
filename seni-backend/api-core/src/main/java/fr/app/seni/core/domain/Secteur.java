package fr.app.seni.core.domain;

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
@Table(name = "secteur")
public class Secteur {

    @Id
    @Column(name = "id_secteur", nullable = false, length = 254)
    private String idSecteur;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_ville", nullable = false)
    private Ville ville;

    @Column(name = "libelle", nullable = false, length = 254)
    private String libelle;

}