package pl.futurniture.futurniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.futurniture.futurniture.model.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

}
