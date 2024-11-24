package fr.app.seni.core.repository;

import fr.app.seni.core.domain.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Province, String> {

    @Query("from Province p where p.region.idRegion = ?1")
    List<Province> findByRegion(String idRegion);
}