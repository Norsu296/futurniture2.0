package pl.futurniture.futurniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.futurniture.futurniture.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
