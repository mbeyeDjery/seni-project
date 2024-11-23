package fr.app.seni.core.repository;

import fr.app.seni.core.domain.TypeHopital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeHopitalRepository extends JpaRepository<TypeHopital, String> {

    List<TypeHopital> findByEnabled(Boolean enabled);
}