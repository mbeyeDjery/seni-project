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
@Table(name = "province")
public class Province {

    @Id
    @Column(name = "id_province", nullable = false, length = 254)
    private String idProvince;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_region", nullable = false)
    @ToString.Exclude
    private Region region;

    @Column(name = "nom", nullable = false, length = 254)
    private String nom;

}