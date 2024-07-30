package saidane.khalil.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import saidane.khalil.catalog.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from Product p where p.price between min and max", nativeQuery = true)
    List<Product> findAllWithPriceRange(float min, float max);
}
