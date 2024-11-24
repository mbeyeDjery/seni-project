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
@Table(name = "notation_hopital")
public class NotationHopital {

    @Id
    @Column(name = "id_notation", nullable = false, length = 254)
    private String idNotation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_hopital", nullable = false)
    @ToString.Exclude
    private Hopital hopital;

    @Column(name = "id_user", nullable = false, length = 254)
    private String idUser;

    @Column(name = "note", nullable = false)
    private Double note;

}