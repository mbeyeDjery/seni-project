package fr.app.seni.core.repository;

import fr.app.seni.core.domain.TypeHopital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeHopitalRepository extends JpaRepository<TypeHopital, String> {
}