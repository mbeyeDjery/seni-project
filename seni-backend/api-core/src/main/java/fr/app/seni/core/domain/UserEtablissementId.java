package fr.app.seni.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@Embeddable
public class UserEtablissementId implements Serializable {

    @Column(name = "id_user", nullable = false, length = 254)
    private String idUser;

    @Column(name = "id_etablissemnt", nullable = false, length = 254)
    private String idEtablissemnt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEtablissementId entity = (UserEtablissementId) o;
        return Objects.equals(this.idUser, entity.idUser) &&
                Objects.equals(this.idEtablissemnt, entity.idEtablissemnt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idEtablissemnt);
    }

}