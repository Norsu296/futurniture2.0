package pl.futurniture.futurniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.futurniture.futurniture.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
