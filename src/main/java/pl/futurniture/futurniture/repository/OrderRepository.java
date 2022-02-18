package pl.futurniture.futurniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.futurniture.futurniture.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
