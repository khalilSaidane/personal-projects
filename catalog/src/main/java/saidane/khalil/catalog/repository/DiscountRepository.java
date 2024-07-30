package saidane.khalil.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saidane.khalil.catalog.model.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
