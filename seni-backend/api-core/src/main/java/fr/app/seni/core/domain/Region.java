package fr.app.seni.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "region")
public class Region {

    @Id
    @Column(name = "id_region", nullable = false, length = 254)
    private String idRegion;

    @Column(name = "code_insd", length = 254)
    private String codeInsd;

    @Column(name = "nom", nullable = false, length = 254)
    private String nom;

    @Column(name = "description", length = 254)
    private String description;

}