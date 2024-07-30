package saidane.khalil.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saidane.khalil.catalog.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
