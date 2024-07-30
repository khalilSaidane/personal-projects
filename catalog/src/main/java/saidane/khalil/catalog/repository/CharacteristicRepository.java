package saidane.khalil.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saidane.khalil.catalog.model.Characteristic;

@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
}
