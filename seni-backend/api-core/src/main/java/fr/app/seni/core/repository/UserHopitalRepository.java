package fr.app.seni.core.repository;

import fr.app.seni.core.domain.UserHopital;
import fr.app.seni.core.domain.UserHopitalId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHopitalRepository extends JpaRepository<UserHopital, UserHopitalId> {
}