package fr.app.seni.core.repository;

import fr.app.seni.core.domain.UserEtablissement;
import fr.app.seni.core.domain.UserEtablissementId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEtablissementRepository extends JpaRepository<UserEtablissement, UserEtablissementId> {
}