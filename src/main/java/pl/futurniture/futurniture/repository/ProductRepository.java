package pl.futurniture.futurniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.futurniture.futurniture.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
