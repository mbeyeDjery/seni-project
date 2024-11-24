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
@Table(name = "ville")
public class Ville {

    @Id
    @Column(name = "id_ville", nullable = false, length = 254)
    private String idVille;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_province", nullable = false)
    private Province province;

    @Column(name = "nom", nullable = false, length = 254)
    private String nom;

}