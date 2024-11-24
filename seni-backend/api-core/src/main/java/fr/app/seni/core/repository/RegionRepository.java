package fr.app.seni.core.repository;

import fr.app.seni.core.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, String> {
}