package pl.futurniture.futurniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.futurniture.futurniture.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {


    Optional<Category> findByName(String name);
}
