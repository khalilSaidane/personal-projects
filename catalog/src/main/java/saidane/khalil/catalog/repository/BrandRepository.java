package saidane.khalil.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saidane.khalil.catalog.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
