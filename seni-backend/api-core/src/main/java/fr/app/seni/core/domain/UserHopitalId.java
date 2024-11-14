package fr.app.seni.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@Embeddable
public class UserHopitalId implements Serializable {

    @Column(name = "id_user", nullable = false, length = 254)
    private String idUser;

    @Column(name = "id_hopital", nullable = false, length = 254)
    private String idHopital;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserHopitalId entity = (UserHopitalId) o;
        return Objects.equals(this.idUser, entity.idUser) &&
                Objects.equals(this.idHopital, entity.idHopital);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idHopital);
    }

}