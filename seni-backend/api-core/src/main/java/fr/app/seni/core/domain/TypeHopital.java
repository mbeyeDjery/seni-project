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
@Table(name = "type_hopital")
public class TypeHopital {
    @Id
    @Column(name = "id_type_hopital", nullable = false, length = 254)
    private String idTypeHopital;

    @Column(name = "libelle", length = 254)
    private String libelle;

    @Column(name = "description", length = 254)
    private String description;

    @Column(name = "enabled")
    private Boolean enabled;

}